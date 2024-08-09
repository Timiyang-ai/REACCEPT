@Test
	public void add_update()
	{
		// guard
		assertThat(candidateRepository.retrieveStockAt(mkQuery(now)).get(), is(stockCandidate));
		final List<Candidate> stockBeforeReplacement = candidateRepository.retrieveStockFrom(mkQuery(now));
		assertThat(stockBeforeReplacement.size(), is(2));
		assertThat(stockBeforeReplacement, contains(stockCandidate, laterStockCandidate));

		final Candidate replacementCandidate = stockCandidate.withOtherQuantity(new Quantity(BigDecimal.ONE, uom));
		candidateRepository.add(replacementCandidate);

		assertThat(candidateRepository.retrieveStockAt(mkQuery(now)).get(), is(replacementCandidate));
		final List<Candidate> stockAfterReplacement = candidateRepository.retrieveStockFrom(mkQuery(now));
		assertThat(stockAfterReplacement.size(), is(2));
		assertThat(stockAfterReplacement, contains(replacementCandidate, laterStockCandidate));
	}