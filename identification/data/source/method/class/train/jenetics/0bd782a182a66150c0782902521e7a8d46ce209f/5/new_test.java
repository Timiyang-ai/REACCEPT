	@Test
	public void toCanonicalString() {
		BitChromosome c = BitChromosome.of(BigInteger.valueOf(234902));
		String value = c.toCanonicalString();
		BitChromosome sc = BitChromosome.of(value);

		Assert.assertEquals(sc, c);
	}