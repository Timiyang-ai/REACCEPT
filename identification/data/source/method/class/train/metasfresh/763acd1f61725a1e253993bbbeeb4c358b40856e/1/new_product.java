public void applyDeltaToMatchingLaterStockCandidates(
			@NonNull final MaterialDescriptor materialDescriptor,
			@NonNull final Integer groupId,
			@NonNull final BigDecimal delta)
	{
		final MaterialDescriptorQuery materialDescriptorQuery = MaterialDescriptorQuery
				.forDescriptor(materialDescriptor, DateOperator.AFTER);

		final CandidatesQuery query = CandidatesQuery.builder()
				.type(CandidateType.STOCK)
				.materialDescriptorQuery(materialDescriptorQuery)
				.parentId(CandidatesQuery.UNSPECIFIED_PARENT_ID)
				.matchExactStorageAttributesKey(true)
				.build();

		final List<Candidate> candidatesToUpdate = candidateRepositoryRetrieval.retrieveOrderedByDateAndSeqNo(query);
		for (final Candidate candidate : candidatesToUpdate)
		{
			final BigDecimal newQty = candidate.getQuantity().add(delta);
			candidateRepositoryWriteService.updateCandidate(candidate
					.withQuantity(newQty)
					.withGroupId(groupId));
		}
	}