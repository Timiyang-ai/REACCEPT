public Candidate onSupplyCandidateNewOrChange(@NonNull final Candidate supplyCandidate)
	{
		Preconditions.checkArgument(supplyCandidate.getType() == Type.SUPPLY, "Given parameter 'supplyCandidate' has type=%s; supplyCandidate=%s", supplyCandidate.getType(), supplyCandidate);

		// store the supply candidate and get both it's ID and qty-delta
		final Candidate supplyCandidateDeltaWithId = candidateRepository.addOrUpdate(supplyCandidate);

		if (supplyCandidateDeltaWithId.getQuantity().signum() == 0)
		{
			return supplyCandidateDeltaWithId; // nothing to do
		}

		final Candidate parentStockCandidateWithId;
		if (supplyCandidateDeltaWithId.getParentIdNotNull() > 0)
		{
			// this supply candidate is not new and already has a stock candidate as its parent
			parentStockCandidateWithId = updateStock(
					supplyCandidateDeltaWithId,
					() -> {
						// don't check if we might create a new stock candidate, because we know we don't. Get the one that already exists and just update its quantity
						final Candidate stockCandidate = candidateRepository.retrieve(supplyCandidateDeltaWithId.getParentId());
						return candidateRepository.updateQty(
								stockCandidate.withQuantity(
										stockCandidate.getQuantity().add(supplyCandidateDeltaWithId.getQuantity())));
					});
		}
		else
		{
			// update (or add) the stock with the delta
			parentStockCandidateWithId = addOrUpdateStock(supplyCandidateDeltaWithId
					// but don't provide the supply's SeqNo, because there might already be a stock record which we might override (even if the supply candidate is not yet linked to it);
					// plus, the supply's seqNo shall depend on the stock's anyways
					.withSeqNo(null));
		}

		// set the stock candidate as parent for the supply candidate
		// the return value would have qty=0, but in the repository we updated the parent-ID
		candidateRepository.addOrUpdate(
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