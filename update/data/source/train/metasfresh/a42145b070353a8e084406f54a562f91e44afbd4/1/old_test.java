@Test
	public void addOrReplace_update()
	{
		// guard
		assertThat(candidateRepository.retrieveLatestMatch(mkStockUntilSegment(now)).isPresent(), is(true));
		assertThat(candidateRepository.retrieveLatestMatch(mkStockUntilSegment(now)).get(), is(stockCandidate));
		final List<Candidate> stockBeforeReplacement = candidateRepository.retrieveMatches(mkStockFromSegment(now));
		assertThat(stockBeforeReplacement.size(), is(2));
		assertThat(stockBeforeReplacement.stream().collect(Collectors.toList()), contains(stockCandidate, laterStockCandidate));

		final Candidate replacementCandidate = stockCandidate.withQuantity(BigDecimal.ONE);
		candidateRepository.addOrReplace(replacementCandidate);

		assertThat(candidateRepository.retrieveLatestMatch(mkStockUntilSegment(now)).get(), is(replacementCandidate));
		final List<Candidate> stockAfterReplacement = candidateRepository.retrieveMatches(mkStockFromSegment(now));
		assertThat(stockAfterReplacement.size(), is(2));
		assertThat(stockAfterReplacement.stream().collect(Collectors.toList()), contains(replacementCandidate, laterStockCandidate));
	}