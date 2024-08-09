	@Test(dataProvider = "engineParams")
	public <G extends Gene<?, G>> void variableLengthChromosomes(
		final Genotype<G> gtf,
		final Alterer<G, Double> alterer,
		final Selector<G, Double> selector
	) {
		final Random random = new Random(123);
		final Engine<G, Double> engine = Engine
			.builder(gt -> random.nextDouble(), gtf)
			.alterers(alterer)
			.selector(selector)
			.build();

		final EvolutionResult<G, Double> result = engine.stream()
			.limit(50)
			.collect(EvolutionResult.toBestEvolutionResult());
	}