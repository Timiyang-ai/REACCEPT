@Test
	public void retrieveStockAt()
	{
		final CandidatesSegment earlierQuery = mkSegment(earlier);
		final Optional<Candidate> earlierStock = candidateRepository.retrieveStockAt(earlierQuery);
		assertThat(earlierStock.isPresent(), is(false));

		final CandidatesSegment sameTimeQuery = mkSegment(now);
		final Optional<Candidate> sameTimeStock = candidateRepository.retrieveStockAt(sameTimeQuery);
		assertThat(sameTimeStock.isPresent(), is(true));
		assertThat(toCandidateWithoutIds(sameTimeStock.get()), is(stockCandidate));

		final CandidatesSegment laterQuery = mkSegment(later);
		final Optional<Candidate> laterStock = candidateRepository.retrieveStockAt(laterQuery);
		assertThat(laterStock.isPresent(), is(true));
		assertThat(toCandidateWithoutIds(laterStock.get()), is(laterStockCandidate));
	}