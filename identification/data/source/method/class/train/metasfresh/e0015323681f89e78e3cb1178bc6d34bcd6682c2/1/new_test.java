@Test
	public void retrieveStockAt()
	{
		final CandidatesSegment earlierQuery = mkStockUntilSegment(earlier);
		final Optional<Candidate> earlierStock = candidateRepository.retrieveLatestMatch(earlierQuery);
		assertThat(earlierStock.isPresent(), is(false));

		final CandidatesSegment sameTimeQuery = mkStockUntilSegment(now);
		final Optional<Candidate> sameTimeStock = candidateRepository.retrieveLatestMatch(sameTimeQuery);
		assertThat(sameTimeStock.isPresent(), is(true));
		assertThat(toCandidateWithoutIds(sameTimeStock.get()), is(stockCandidate));

		final CandidatesSegment laterQuery = mkStockUntilSegment(later);
		final Optional<Candidate> laterStock = candidateRepository.retrieveLatestMatch(laterQuery);
		assertThat(laterStock.isPresent(), is(true));
		assertThat(toCandidateWithoutIds(laterStock.get()), is(laterStockCandidate));
	}