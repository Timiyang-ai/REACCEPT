@Override
	public Candidate onCandidateNewOrChange(@NonNull final Candidate candidate)
	{
		assertCorrectCandidateType(candidate);

		final SaveResult candidateSaveResult = candidateRepositoryWriteService.addOrUpdateOverwriteStoredSeqNo(candidate);

		if (!candidateSaveResult.isDateChanged() && !candidateSaveResult.isQtyChanged())
		{
			return candidateSaveResult.toCandidateWithQtyDelta(); // nothing to do
		}

		final Candidate stockCandidate;

		final Candidate savedCandidate = candidateSaveResult.getCandidate();
		final Optional<Candidate> childStockCandidate = candidateRepository.retrieveSingleChild(savedCandidate.getId());
		if (childStockCandidate.isPresent())
		{
			stockCandidate = stockCandidateService
					.createStockCandidate(savedCandidate.withNegatedQuantity())
					.withId(childStockCandidate.get().getId());
		}
		else
		{
			stockCandidate = stockCandidateService
					.createStockCandidate(savedCandidate.withNegatedQuantity());
		}

		final Candidate savedStockCandidate = candidateRepositoryWriteService
				.addOrUpdateOverwriteStoredSeqNo(stockCandidate.withParentId(savedCandidate.getId()))
				.getCandidate();

		final SaveResult deltaToApplyToLaterStockCandiates = SaveResult.builder()
				.previousQty(candidateSaveResult.getPreviousQty())
				.previousTime(candidateSaveResult.getPreviousTime())
				.candidate(savedCandidate)
				.build();

		stockCandidateService.applyDeltaToMatchingLaterStockCandidates(deltaToApplyToLaterStockCandiates);

		// set the stock candidate as child for the demand candidate
		candidateRepositoryWriteService.updateCandidateById(
				savedCandidate
						.withParentId(savedStockCandidate.getId()));

		final Candidate candidateToReturn = candidateSaveResult
				.toCandidateWithQtyDelta()
				.withParentId(savedStockCandidate.getId());

		if (savedCandidate.getType() == CandidateType.DEMAND)
		{
			fireSupplyRequiredEventIfQtyBelowZero(candidateToReturn);
		}
		return candidateToReturn;
	}