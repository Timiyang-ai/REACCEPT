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
			// check if there is a supply record with the same demand detail and material descriptor
			final Candidate existingSupplyParentStockWithoutParentId = retrieveSupplyParentStockWithoutParentIdOrNull(demandCandidateWithId);
			if (existingSupplyParentStockWithoutParentId != null)
			{
				final Candidate existingSupplyParentStockWithUpdatedQty = existingSupplyParentStockWithoutParentId
						.withQuantity(existingSupplyParentStockWithoutParentId.getQuantity().subtract(demandCandidateWithId.getQuantity()))
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

		candidateRepositoryWriteService.updateCandidate(childStockWithDemand
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

		if (demandCandidateWithId.getType() == CandidateType.DEMAND)
		{
			fireSupplyRequiredEventIfQtyBelowZero(demandCandidateWithId);
		}
		return demandCandidateToReturn;
	}