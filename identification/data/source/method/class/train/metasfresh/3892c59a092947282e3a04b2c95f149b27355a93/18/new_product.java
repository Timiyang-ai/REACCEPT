@Override
	public Candidate onCandidateNewOrChange(@NonNull final Candidate candidate)
	{
		assertCorrectCandidateType(candidate);

		final SaveResult candidateSaveResult = candidateRepositoryWriteService.addOrUpdateOverwriteStoredSeqNo(candidate);

		if (!candidateSaveResult.isDateChanged() && !candidateSaveResult.isQtyChanged())
		{
			return candidateSaveResult.toCandidateWithQtyDelta(); // nothing to do
		}

		final Candidate savedCandidate = candidateSaveResult.getCandidate();

		final Optional<Candidate> preExistingChildStockCandidate = candidateRepository.retrieveSingleChild(savedCandidate.getId());
		final CandidateId preExistingChildStockId = preExistingChildStockCandidate.isPresent() ? preExistingChildStockCandidate.get().getId() : null;

		final SaveResult stockCandidate = stockCandidateService
				.createStockCandidate(savedCandidate.withNegatedQuantity())
				.withCandidateId(preExistingChildStockId);

		final Candidate savedStockCandidate = candidateRepositoryWriteService
				.addOrUpdateOverwriteStoredSeqNo(stockCandidate.getCandidate().withParentId(savedCandidate.getId()))
				.getCandidate();

		final SaveResult deltaToApplyToLaterStockCandiates = candidateSaveResult.withNegatedQuantity();

		stockCandidateService.applyDeltaToMatchingLaterStockCandidates(deltaToApplyToLaterStockCandiates);

		final Candidate candidateToReturn = candidateSaveResult
				.toCandidateWithQtyDelta()
				.withParentId(savedStockCandidate.getId());

		if (savedCandidate.getType() == CandidateType.DEMAND)
		{
			fireSupplyRequiredEventIfQtyBelowZero(candidateToReturn);
		}
		return candidateToReturn;
	}