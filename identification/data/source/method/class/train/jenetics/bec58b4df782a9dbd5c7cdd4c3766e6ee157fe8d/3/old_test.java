	@Test(invocationCount = 20, successPercentage = 90)
	public void newInstance() {
		final int size = 50_000;
		final BitChromosome base = BitChromosome.of(size, 0.5);

		for (int i = 0; i < 100; ++i) {
			final BitChromosome other = base.newInstance();
			Assert.assertNotEquals(other, base);

			Assert.assertEquals(other.bitCount(), size/2.0, size/100.0);
		}
	}