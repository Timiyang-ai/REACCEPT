	@Test
	public void constraint() {
		final int populationSize = 100;

		final Engine<DoubleGene, Double> engine = Engine
			.builder(a -> a.getGene().getAllele(), DoubleChromosome.of(0, 1))
			.constraint(RetryConstraint.of(pt -> false))
			.populationSize(populationSize)
			.build();

		final EvolutionResult<DoubleGene, Double> result = engine.stream()
			.limit(10)
			.collect(EvolutionResult.toBestEvolutionResult());

		Assert.assertEquals(result.getInvalidCount(), populationSize);
	}