@VisibleForTesting
	/* package */ void applyDeltaToMatchingLaterStockCandidates(
			@NonNull final ProductDescriptor productDescriptor,
			@NonNull final Integer warehouseId,
			@NonNull final Date date,
			@NonNull final Integer groupId,
			@NonNull final BigDecimal delta)
	{
		final CandidatesQuery query = CandidatesQuery.builder()
				.type(CandidateType.STOCK)
				.materialDescriptor(MaterialDescriptor.builderForQuery()
						.date(date)
						.productDescriptor(productDescriptor)
						.warehouseId(warehouseId)
						.dateOperator(DateOperator.AFTER)
						.build())
				.matchExactStorageAttributesKey(true)
				.build();

		final List<Candidate> candidatesToUpdate = candidateRepository.retrieveOrderedByDateAndSeqNo(query);
		for (final Candidate candidate : candidatesToUpdate)
		{
			final BigDecimal newQty = candidate.getQuantity().add(delta);
			candidateRepositoryCommands.addOrUpdateOverwriteStoredSeqNo(candidate
					.withQuantity(newQty)
					.withGroupId(groupId));
		}
	}