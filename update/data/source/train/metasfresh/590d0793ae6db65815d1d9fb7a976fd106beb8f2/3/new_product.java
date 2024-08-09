public void applyDeltaToMatchingLaterStockCandidates(
			@NonNull final SaveResult stockWithDelta)
	{
		final CandidatesQuery query = createStockQueryBetweenDates(stockWithDelta);

		final BigDecimal deltaUntilRangeEnd;
		final BigDecimal deltaAfterRangeEnd;
		if (stockWithDelta.isDateMoved())
		{
			if (stockWithDelta.isDateMovedForwards())
			{
				deltaUntilRangeEnd = stockWithDelta.getCandidate().getQuantity().negate();
				deltaAfterRangeEnd = stockWithDelta.getQtyDelta();
			}
			else
			{
				deltaUntilRangeEnd = stockWithDelta.getCandidate().getQuantity();
				deltaAfterRangeEnd = stockWithDelta.getQtyDelta();
			}
		}
		else
		{
			deltaUntilRangeEnd = stockWithDelta.getQtyDelta();
			deltaAfterRangeEnd = null;
		}

		final List<Candidate> candidatesToUpdateWithinRange = candidateRepositoryRetrieval.retrieveOrderedByDateAndSeqNo(query);
		for (final Candidate candidate : candidatesToUpdateWithinRange)
		{
			final BigDecimal newQty = candidate.getQuantity().add(deltaUntilRangeEnd);

			candidateRepositoryWriteService.updateCandidateById(candidate
					.withQuantity(newQty)
					.withGroupId(stockWithDelta.getCandidate().getGroupId()));
		}
		if (deltaAfterRangeEnd == null || deltaAfterRangeEnd.signum() == 0)
		{
			return; // we are done
		}

		final MaterialDescriptorQuery materialDescriptorQuery = query.getMaterialDescriptorQuery();
		final MaterialDescriptorQuery materialDescriptToQueryAfterRange = materialDescriptorQuery.toBuilder()
				.timeRangeStart(materialDescriptorQuery.getTimeRangeEnd())
				.timeRangeEnd(null)
				.build();
		final CandidatesQuery queryAfterRange = query.withMaterialDescriptorQuery(materialDescriptToQueryAfterRange);
		final List<Candidate> candidatesToUpdateAfterRange = candidateRepositoryRetrieval.retrieveOrderedByDateAndSeqNo(queryAfterRange);
		for (final Candidate candidate : candidatesToUpdateAfterRange)
		{
			final BigDecimal newQty = candidate.getQuantity().add(deltaAfterRangeEnd);

			candidateRepositoryWriteService.updateCandidateById(candidate
					.withQuantity(newQty)
					.withGroupId(stockWithDelta.getCandidate().getGroupId()));
		}
	}