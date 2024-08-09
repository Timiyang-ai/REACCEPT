public void applyDeltaToMatchingLaterStockCandidates(
			@NonNull final Candidate stockWithDelta)
	{
		final CandidatesQuery query = createStockQueryBuilderWithDateOperator(
				stockWithDelta,
				DateOperator.AT_OR_AFTER);

		final List<Candidate> candidatesToUpdate = candidateRepositoryRetrieval.retrieveOrderedByDateAndSeqNo(query);
		for (final Candidate candidate : candidatesToUpdate)
		{
			final boolean sameDateButLowerSeqNo = //
					candidate.getDate().equals(stockWithDelta.getDate())
							&& candidate.getSeqNo() <= stockWithDelta.getSeqNo();
			if (sameDateButLowerSeqNo)
			{
				continue;
			}

			final BigDecimal delta = stockWithDelta.getQuantity();
			final BigDecimal newQty = candidate.getQuantity().add(delta);

			candidateRepositoryWriteService.updateCandidateById(candidate
					.withQuantity(newQty)
					.withGroupId(stockWithDelta.getGroupId()));
		}
	}