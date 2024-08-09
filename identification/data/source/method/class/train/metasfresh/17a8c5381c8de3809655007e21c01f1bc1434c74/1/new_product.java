public void onSupplyCandidateNewOrChange(final Candidate supplyCandidate)
	{
		// store the supply candidate and get both it's ID and qty-delta
		final Candidate supplyCandidateDeltaWithId = candidateRepository.addOrReplace(supplyCandidate);
		
		// update the stock with the delta
		final Candidate parentStockCandidateWithId = updateStock(supplyCandidateDeltaWithId);

		// set the stock candidate as parent for the supply candidate
		candidateRepository.addOrReplace(
				supplyCandidate.withParentId(parentStockCandidateWithId.getId()));

		// e.g.
		// supply-candidate with 23 (i.e. +23)
		// parent-stockCandidate used to have -44 (because of "earlier" candidate)
		// now has -21

		// notify the system about the stock candidate
		// this notification shall lead to all "later" stock candidates with the same product and locator being notified
	}