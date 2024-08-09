@VisibleForTesting
	/* package */ void applyDeltaToLaterStockCandidates(
			@NonNull final ProductDescriptor productDescriptor,
			@NonNull final Integer warehouseId,
			@NonNull final Date date,
			@NonNull final Integer groupId,
			@NonNull final BigDecimal delta)
	{
		final CandidatesQuery segment = CandidatesQuery.builder()
				.type(Type.STOCK)
				.materialDescr(MaterialDescriptor.builderForQuery()
						.date(date)
						.productDescriptor(productDescriptor)
						.warehouseId(warehouseId)
						.dateOperator(DateOperator.AFTER)
						.build())
				.build();

		final List<Candidate> candidatesToUpdate = candidateRepository.retrieveOrderedByDateAndSeqNo(segment);
		for (final Candidate candidate : candidatesToUpdate)
		{
			final BigDecimal newQty = candidate.getQuantity().add(delta);
			candidateRepository.addOrUpdateOverwriteStoredSeqNo(candidate
					.withQuantity(newQty)
					.withGroupId(groupId));
		}
	}