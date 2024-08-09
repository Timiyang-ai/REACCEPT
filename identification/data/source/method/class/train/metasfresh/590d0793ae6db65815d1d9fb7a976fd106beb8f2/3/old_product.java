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
			childStockWithDemand = possibleChildStockCandidate.get().withQuantity(demandCandidate.getQuantity().negate());
			childStockWithDemandDelta = stockCandidateService.updateQty(childStockWithDemand);
		}
		else
		{
			final Candidate templateForNewDemandCandidateChild = demandCandidateDeltaWithId.withNegatedQuantity().withSeqNo(expectedStockSeqNo);
			final Candidate newDemandCandidateChild = stockCandidateService.createStockCandidate(templateForNewDemandCandidateChild);

			childStockWithDemandDelta = candidateRepositoryWriteService
					.addOrUpdatePreserveExistingSeqNo(newDemandCandidateChild);
			childStockWithDemand = childStockWithDemandDelta.withQuantity(newDemandCandidateChild.getQuantity());
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