@Test
	public void retrieveStockAt()
	{
		final CandidatesSegment earlierQuery = mkQuery(earlier);
		final Optional<Candidate> earlierStock = candidateRepository.retrieveStockAt(earlierQuery);
		assertThat(earlierStock.isPresent(), is(false));

		final CandidatesSegment sameTimeQuery = mkQuery(now);
		final Optional<Candidate> sameTimeStock = candidateRepository.retrieveStockAt(sameTimeQuery);
		assertThat(sameTimeStock.isPresent(), is(true));
		assertThat(sameTimeStock.get(), is(stockCandidate));

		final CandidatesSegment laterQuery = mkQuery(later);
		final Optional<Candidate> laterStock = candidateRepository.retrieveStockAt(laterQuery);
		assertThat(laterStock.isPresent(), is(true));
		assertThat(laterStock.get(), is(laterStockCandidate));
	}