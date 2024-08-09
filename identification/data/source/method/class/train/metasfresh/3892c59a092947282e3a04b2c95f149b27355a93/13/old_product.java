@VisibleForTesting
	/* package */ void applyDeltaToLaterStockCandidates(
			@NonNull final Integer productId,
			@NonNull final Integer warehouseId,
			@NonNull final Date date,
			@NonNull final Integer groupId,
			@NonNull final BigDecimal delta)
	{
		final CandidatesQuery segment = CandidatesQuery.builder()
				.type(Type.STOCK)
				.date(date)
				.dateOperator(DateOperator.after)
				.productId(productId)
				.warehouseId(warehouseId)
				.build();

		final List<Candidate> candidatesToUpdate = candidateRepository.retrieveMatchesOrderByDateAndSeqNo(segment);
		for (final Candidate candidate : candidatesToUpdate)
		{
			final BigDecimal newQty = candidate.getQuantity().add(delta);
			candidateRepository.addOrUpdateOverwriteStoredSeqNo(candidate
					.withQuantity(newQty)
					.withGroupId(groupId));
		}
	}