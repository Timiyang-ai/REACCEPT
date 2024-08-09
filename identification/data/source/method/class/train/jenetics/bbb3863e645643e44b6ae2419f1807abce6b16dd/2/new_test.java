	@Test
	public void invert() {
		final BitChromosome c1 = BitChromosome.of(100, 0.3);
		final BitChromosome c3 = c1.invert();

		for (int i = 0; i < c1.length(); ++i) {
			Assert.assertTrue(c1.getGene(i).getBit() != c3.getGene(i).getBit());
		}

		BitChromosome c4 = c3.invert();
		Assert.assertEquals(c4, c1);
	}