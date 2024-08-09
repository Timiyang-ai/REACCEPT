@Test
	public void add_update()
	{
		// guard
		assertThat(candidateRepository.retrieveStockAt(mkQueryWithLocator(now)).isPresent(), is(true));
		assertThat(toCandidateWithoutIds(candidateRepository.retrieveStockAt(mkQueryWithLocator(now)).get()), is(stockCandidate));
		final List<Candidate> stockBeforeReplacement = candidateRepository.retrieveStockFrom(mkQueryWithLocator(now));
		assertThat(stockBeforeReplacement.size(), is(2));
		assertThat(stockBeforeReplacement.stream().map(c -> toCandidateWithoutIds(c)).collect(Collectors.toList()), contains(stockCandidate, laterStockCandidate));

		final Candidate replacementCandidate = stockCandidate.withQuantity(BigDecimal.ONE);
		candidateRepository.addOrReplace(replacementCandidate);

		assertThat(toCandidateWithoutIds(candidateRepository.retrieveStockAt(mkQueryWithLocator(now)).get()), is(replacementCandidate));
		final List<Candidate> stockAfterReplacement = candidateRepository.retrieveStockFrom(mkQueryWithLocator(now));
		assertThat(stockAfterReplacement.size(), is(2));
		assertThat(stockAfterReplacement.stream().map(c -> toCandidateWithoutIds(c)).collect(Collectors.toList()), contains(replacementCandidate, laterStockCandidate));
	}