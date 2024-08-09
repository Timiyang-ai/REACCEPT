@Override
	public Candidate onCandidateNewOrChange(@NonNull final Candidate candidate)
	{
		assertCorrectCandidateType(candidate);

		// store the supply candidate and get both its ID and qty-delta
		// TODO 3034 test: if we add a supplyCandidate that has an unspecified parent-id and and in DB there is an MD_Candidate with parentId > 0,
		// then supplyCandidateDeltaWithId needs to have that parentId
		final SaveResult candidateSaveResult = candidateRepositoryWriteService.addOrUpdateOverwriteStoredSeqNo(candidate);

		if (!candidateSaveResult.isDateChanged() && !candidateSaveResult.isQtyChanged())
		{
			return candidateSaveResult.toCandidateWithQtyDelta(); // nothing to do
		}

		final Candidate savedCandidate = candidateSaveResult.getCandidate();

		final SaveResult stockCandidate = stockCandidateService
				.createStockCandidate(savedCandidate)
				.withCandidateId(savedCandidate.getParentId());

		final Candidate savedStockCandidate = candidateRepositoryWriteService
				.addOrUpdateOverwriteStoredSeqNo(stockCandidate.getCandidate())
				.getCandidate();

		final SaveResult deltaToApplyToLaterStockCandiates = SaveResult.builder()
				//.candidate(stockCandidate.getCandidate())
				//.previousQty(stockCandidate.getPreviousQty())
				.candidate(savedCandidate)
				.previousQty(candidateSaveResult.getPreviousQty())
				.previousTime(candidateSaveResult.getPreviousTime())
				.build();

		stockCandidateService.applyDeltaToMatchingLaterStockCandidates(deltaToApplyToLaterStockCandiates);

		// set the stock candidate as parent for the supply candidate
		candidateRepositoryWriteService.updateCandidateById(
				savedCandidate
						.withParentId(savedStockCandidate.getId()));

		return candidateSaveResult
				.toCandidateWithQtyDelta()
				.withParentId(savedStockCandidate.getId());
	}