	@Test
	public void toBitSet() {
		BitChromosome c1 = BitChromosome.of(34);
		BitChromosome c2 = BitChromosome.of(c1.toBitSet(), 34);

		for (int i = 0; i < c1.length(); ++i) {
			assertEquals(c1.getGene(i).getBit(), c2.getGene(i).getBit());
		}
	}