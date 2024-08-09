public Candidate onSupplyCandidateNewOrChange(final Candidate supplyCandidate)
	{
		Preconditions.checkArgument(supplyCandidate.getType() == Type.SUPPLY, "Given parameter 'supplyCandidate' has type=%s; supplyCandidate=%s", supplyCandidate.getType(), supplyCandidate);

		// store the supply candidate and get both it's ID and qty-delta
		final Candidate supplyCandidateDeltaWithId = candidateRepository.addOrReplace(supplyCandidate);

		if (supplyCandidateDeltaWithId.getQuantity().signum() == 0)
		{
			return supplyCandidateDeltaWithId; // nothing to do
		}

		// update the stock with the delta
		final Candidate parentStockCandidateWithId = updateStock(supplyCandidateDeltaWithId
				.withSeqNo(null) // don't provide the supply's SeqNo, because there might already be a stock record which we might override; plus, the supply's seqNo shall depend on the stock's anyways
		);

		// set the stock candidate as parent for the supply candidate
		// the return value would have qty=0, but in the repository we updated the parent-ID
		candidateRepository.addOrReplace(
				supplyCandidate
						.withParentId(parentStockCandidateWithId.getId())
						.withSeqNo(parentStockCandidateWithId.getSeqNo() + 1));

		return supplyCandidateDeltaWithId
				.withParentId(parentStockCandidateWithId.getId())
				.withSeqNo(parentStockCandidateWithId.getSeqNo() + 1);

		// e.g.
		// supply-candidate with 23 (i.e. +23)
		// parent-stockCandidate used to have -44 (because of "earlier" candidate)
		// now has -21
	}