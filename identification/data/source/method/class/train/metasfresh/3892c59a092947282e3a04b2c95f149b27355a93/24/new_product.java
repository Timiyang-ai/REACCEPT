@Override
	public Candidate onCandidateNewOrChange(@NonNull final Candidate demandCandidate)
	{
		assertCorrectCandidateType(demandCandidate);

		final Candidate demandCandidateWithId = candidateRepositoryWriteService
				.addOrUpdateOverwriteStoredSeqNo(demandCandidate);

		if (demandCandidateWithId.getQuantity().signum() == 0)
		{
			// this candidate didn't change anything
			return demandCandidateWithId;
		}

		// this is the seqno which the new stock candidate shall get according to the demand candidate
		final int expectedStockSeqNo = demandCandidateWithId.getSeqNo() + 1;

		final Candidate childStockWithDemand;

		final Optional<Candidate> possibleChildStockCandidate = candidateRepository.retrieveSingleChild(demandCandidateWithId.getId());
		if (possibleChildStockCandidate.isPresent())
		{
			// this supply candidate is not new and already has a stock candidate as its parent. be sure to update exactly *that* candidate
			childStockWithDemand = stockCandidateServiceRetrieval
					.updateStock(
							demandCandidateWithId, () -> {
								// don't check if we might create a new stock candidate, because we know we don't.
								// Instead we might run into trouble with CandidateRepository.retrieveExact() and multiple matching records.
								// So get the one that we know already exists and just update its quantity
								final Candidate childStockCandidate = possibleChildStockCandidate.get();
								return candidateRepositoryWriteService
										.updateQty(
												childStockCandidate
														.withQuantity(
																childStockCandidate.getQuantity().subtract(demandCandidateWithId.getQuantity())));
							});
		}
		else
		{
			// check if there is a supply record with the same demand detail and material descriptor, check
			final CandidatesQuery queryForExistingSupply = CandidatesQuery.builder()
					.type(CandidateType.SUPPLY)
					.demandDetail(demandCandidateWithId.getDemandDetail())
					.materialDescriptor(demandCandidateWithId.getMaterialDescriptor().withoutQuantity())
					.build();

			final Candidate existingSupplyParentStockWithoutOwnParentId;
			final Candidate existingSupply = candidateRepository.retrieveLatestMatchOrNull(queryForExistingSupply);
			if (existingSupply != null && existingSupply.getParentId() > 0)
			{
				final Candidate existingSupplyParentStock = candidateRepository.retrieveLatestMatchOrNull(CandidatesQuery.fromId(existingSupply.getParentId()));
				if (existingSupplyParentStock.getParentId() > 0)  // we only want to dock with currently "dangling" stock records
				{
					existingSupplyParentStockWithoutOwnParentId = null;
				}
				else
				{
					existingSupplyParentStockWithoutOwnParentId = existingSupplyParentStock;
				}
			}
			else
			{
				existingSupplyParentStockWithoutOwnParentId = null;
			}
			if (existingSupplyParentStockWithoutOwnParentId != null)
			{
				final Candidate existingSupplyParentWithUpdatedQty = existingSupplyParentStockWithoutOwnParentId
						.withQuantity(existingSupplyParentStockWithoutOwnParentId.getQuantity().subtract(demandCandidateWithId.getQuantity()))
						.withParentId(CandidatesQuery.UNSPECIFIED_PARENT_ID);

				candidateRepositoryWriteService.updateQty(existingSupplyParentWithUpdatedQty);
				childStockWithDemand = existingSupplyParentWithUpdatedQty;
			}
			else
			{
				final Candidate newDemandCandidateChild = stockCandidateServiceRetrieval.createStockCandidate(demandCandidateWithId.withNegatedQuantity());
				childStockWithDemand = candidateRepositoryWriteService
						.addOrUpdatePreserveExistingSeqNo(newDemandCandidateChild);
			}
		}

		candidateRepositoryWriteService.updateOverwriteStoredSeqNo(childStockWithDemand
				.withParentId(demandCandidateWithId.getId()));

		final Candidate demandCandidateToReturn;

		if (childStockWithDemand.getSeqNo() != expectedStockSeqNo)
		{
			// there was already a stock candidate which already had a seqNo.
			// keep it and in turn update the demandCandidate's seqNo accordingly
			demandCandidateToReturn = demandCandidate
					.withSeqNo(childStockWithDemand.getSeqNo() - 1);
			candidateRepositoryWriteService.addOrUpdateOverwriteStoredSeqNo(demandCandidateToReturn);
		}
		else
		{
			demandCandidateToReturn = demandCandidateWithId;
		}

		if (demandCandidate.getType() == CandidateType.DEMAND)
		{
			final MaterialQuery query = MaterialQuery.forMaterialDescriptor(demandCandidate.getMaterialDescriptor());
			final BigDecimal availableQuantity = stockRepository.retrieveAvailableStockQtySum(query);
			final boolean demandExceedsAvailableQty = demandCandidate.getQuantity().compareTo(availableQuantity) > 0;

			if (demandExceedsAvailableQty)
			{
				// there would be no more stock left, so
				// notify whoever is in charge that we have a demand to balance
				final BigDecimal requiredAdditionalQty = demandCandidate.getQuantity().subtract(availableQuantity);

				final SupplyRequiredEvent materialDemandEvent = SupplyRequiredEventCreator.createMaterialDemandEvent(demandCandidateWithId, requiredAdditionalQty);
				materialEventService.fireEvent(materialDemandEvent);
			}
		}
		return demandCandidateToReturn;
	}