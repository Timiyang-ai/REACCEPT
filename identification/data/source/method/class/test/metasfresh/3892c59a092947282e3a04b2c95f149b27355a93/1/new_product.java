@Override
	public Candidate onCandidateNewOrChange(@NonNull final Candidate demandCandidate)
	{
		assertCorrectCandidateType(demandCandidate);

		final Candidate demandCandidateDeltaWithId = candidateRepositoryWriteService
				.addOrUpdateOverwriteStoredSeqNo(demandCandidate);

		if (demandCandidateDeltaWithId.getQuantity().signum() == 0)
		{
			// this candidate didn't change anything
			return demandCandidateDeltaWithId;
		}

		// this is the seqno which the new stock candidate shall get according to the demand candidate
		final int expectedStockSeqNo = demandCandidateDeltaWithId.getSeqNo() + 1;

		final Candidate childStockWithDemand;
		final Candidate childStockWithDemandDelta;

		final Optional<Candidate> possibleChildStockCandidate = candidateRepository.retrieveSingleChild(demandCandidateDeltaWithId.getId());
		if (possibleChildStockCandidate.isPresent())
		{
			childStockWithDemand = possibleChildStockCandidate.get().withQuantity(demandCandidateDeltaWithId.getQuantity().negate());
			childStockWithDemandDelta = stockCandidateService.updateQty(childStockWithDemand);
		}
		else
		{
			// check if there is a supply record with the same demand detail and material descriptor
			final Candidate existingSupplyParentStockWithoutParentId = retrieveSupplyParentStockWithoutParentIdOrNull(demandCandidateDeltaWithId);
			if (existingSupplyParentStockWithoutParentId != null)
			{
				//
				final Candidate existingSupplyParentStockWithUpdatedQty = existingSupplyParentStockWithoutParentId
						.withQuantity(existingSupplyParentStockWithoutParentId.getQuantity().subtract(demandCandidateDeltaWithId.getQuantity()))
						.withParentId(CandidatesQuery.UNSPECIFIED_PARENT_ID);

				childStockWithDemandDelta = stockCandidateService.updateQty(existingSupplyParentStockWithUpdatedQty);
				childStockWithDemand = existingSupplyParentStockWithUpdatedQty;
			}
			else
			{
				final Candidate newDemandCandidateChild = stockCandidateService.createStockCandidate(demandCandidateDeltaWithId.withNegatedQuantity());
				childStockWithDemandDelta = candidateRepositoryWriteService
						.addOrUpdatePreserveExistingSeqNo(newDemandCandidateChild);
				childStockWithDemand = childStockWithDemandDelta.withQuantity(newDemandCandidateChild.getQuantity());
			}
		}

		candidateRepositoryWriteService
				.updateCandidateById(childStockWithDemand.withParentId(demandCandidateDeltaWithId.getId()));

		stockCandidateService
				.applyDeltaToMatchingLaterStockCandidates(childStockWithDemandDelta);

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
			demandCandidateToReturn = demandCandidateDeltaWithId;
		}

		if (demandCandidateDeltaWithId.getType() == CandidateType.DEMAND)
		{
			fireSupplyRequiredEventIfQtyBelowZero(demandCandidateDeltaWithId);
		}
		return demandCandidateToReturn;
	}