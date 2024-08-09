public Candidate onCandidateNewOrChange(@NonNull final Candidate demandCandidate)
	{
		assertTCorrectCandidateType(demandCandidate);

		final Candidate demandCandidateWithId = candidateRepository.addOrUpdateOverwriteStoredSeqNo(demandCandidate);

		if (demandCandidateWithId.getQuantity().signum() == 0)
		{
			// this candidate didn't change anything
			return demandCandidateWithId;
		}

		// this is the seqno which the new stock candidate shall get according to the demand candidate
		final int expectedStockSeqNo = demandCandidateWithId.getSeqNo() + 1;

		final Candidate childStockWithDemand;

		final Optional<Candidate> possibleChildStockCandidate = candidateRepository.retrieveSingleChild(demandCandidateWithId.getId());
		if (possibleChildStockCandidate.isPresent())
		{
			// this supply candidate is not new and already has a stock candidate as its parent. be sure to update exactly *that* scandidate
			childStockWithDemand = stockCandidateService.updateStock(
					demandCandidateWithId, () -> {
						// don't check if we might create a new stock candidate, because we know we don't.
						// Instead we might run into trouble with CandidateRepository.retrieveExact() and multiple matching records.
						// So get the one that we know already exists and just update its quantity
						final Candidate childStockCandidate = possibleChildStockCandidate.get();
						return candidateRepository.updateQty(
								childStockCandidate
										.withQuantity(
												childStockCandidate.getQuantity().subtract(demandCandidateWithId.getQuantity())));
					});
		}

		else
		{
			childStockWithDemand = stockCandidateService.addOrUpdateStock(
					demandCandidate
							.withSeqNo(expectedStockSeqNo)
							.withQuantity(demandCandidateWithId.getQuantity().negate())
							.withParentId(demandCandidateWithId.getId()));
		}

		final Candidate demandCandidateToReturn;

		if (childStockWithDemand.getSeqNo() != expectedStockSeqNo)
		{
			// there was already a stock candidate which already had a seqNo.
			// keep it and in turn update the demandCandidate's seqNo accordingly
			demandCandidateToReturn = demandCandidate
					.withSeqNo(childStockWithDemand.getSeqNo() - 1);
			candidateRepository.addOrUpdateOverwriteStoredSeqNo(demandCandidateToReturn);
		}
		else
		{
			demandCandidateToReturn = demandCandidateWithId;
		}

		final boolean demandExceedsAvailableQty = childStockWithDemand.getQuantity().signum() < 0;
		if (demandExceedsAvailableQty && demandCandidate.getType() == Type.DEMAND)
		{
			// there would be no more stock left, so
			// notify whoever is in charge that we have a demand to balance
			final BigDecimal requiredAdditionalQty = childStockWithDemand.getQuantity().negate();

			final MaterialDemandEvent materialDemandEvent = MaterialDemandEventCreator.createMaterialDemandEvent(demandCandidateWithId, requiredAdditionalQty);
			materialEventService.fireEvent(materialDemandEvent);
		}
		return demandCandidateToReturn;
	}