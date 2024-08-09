	@Test
	public void crossover() {
		final ISeq<CharacterGene> g1 = CharacterChromosome.of("1234567890").toSeq();
		final ISeq<CharacterGene> g2 = CharacterChromosome.of("abcdefghij").toSeq();

		final int rv1 = 12;
		using(new Random(10), r -> {
			final UniformCrossover<CharacterGene, Double>
				crossover = new UniformCrossover<>(0.5, 0.5);

			MSeq<CharacterGene> g1c = g1.copy();
			MSeq<CharacterGene> g2c = g2.copy();
			final int changed = crossover.crossover(g1c, g2c);

			Assert.assertEquals(changed,
				IntStream.range(0, g2c.length())
					.filter(i -> Character.isDigit(g2c.get(i).charValue()))
					.count()
			);
		});
	}