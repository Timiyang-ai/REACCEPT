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
		final Candidate childStockWithDemandDelta;

		final Optional<Candidate> possibleChildStockCandidate = candidateRepository.retrieveSingleChild(demandCandidateWithId.getId());
		if (possibleChildStockCandidate.isPresent())
		{
			childStockWithDemand = possibleChildStockCandidate.get().withQuantity(demandCandidate.getQuantity().negate());
			childStockWithDemandDelta = stockCandidateService.updateQty(childStockWithDemand);
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
				final Candidate existingSupplyParentStockWithUpdatedQty = existingSupplyParentStockWithoutOwnParentId
						.withQuantity(existingSupplyParentStockWithoutOwnParentId.getQuantity().subtract(demandCandidateWithId.getQuantity()))
						.withParentId(CandidatesQuery.UNSPECIFIED_PARENT_ID);

				childStockWithDemandDelta = stockCandidateService.updateQty(existingSupplyParentStockWithUpdatedQty);
				childStockWithDemand = existingSupplyParentStockWithUpdatedQty;
			}
			else
			{
				final Candidate newDemandCandidateChild = stockCandidateService.createStockCandidate(demandCandidateWithId.withNegatedQuantity());
				childStockWithDemandDelta = candidateRepositoryWriteService
						.addOrUpdatePreserveExistingSeqNo(newDemandCandidateChild);
				childStockWithDemand = childStockWithDemandDelta.withQuantity(newDemandCandidateChild.getQuantity());
			}
		}

		candidateRepositoryWriteService.updateOverwriteStoredSeqNo(childStockWithDemand
				.withParentId(demandCandidateWithId.getId()));

		final BigDecimal delta = childStockWithDemandDelta.getQuantity();
		stockCandidateService.applyDeltaToMatchingLaterStockCandidates(
				childStockWithDemandDelta.getMaterialDescriptor(),
				childStockWithDemandDelta.getGroupId(),
				delta);

		final Candidate demandCandidateToReturn;

		if (childStockWithDemandDelta.getSeqNo() != expectedStockSeqNo)
		{
			// there was already a stock candidate which already had a seqNo.
			// keep it and in turn update the demandCandidate's seqNo accordingly
			demandCandidateToReturn = demandCandidate
					.withSeqNo(childStockWithDemandDelta.getSeqNo() - 1);
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