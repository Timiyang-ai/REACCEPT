public void onSupplyCandidateNewOrChange(final Candidate supplyCandidate)
	{
		final Candidate parentStockCandidateWithId = updateStock(supplyCandidate);

		candidateRepository.addOrReplace(supplyCandidate.withParentId(parentStockCandidateWithId.getId()));

		// e.g.
		// supply-candidate with 23 (i.e. +23)
		// parent-stockCandidate used to have -44 (because of "earlier" candidate)
		// now has -21

		// notify the system about the stock candidate
		// this notification shall lead to all "later" stock candidates with the same product and locator being notified
	}