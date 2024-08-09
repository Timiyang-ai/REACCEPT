	@Test
	public void cdf() {
		final Range<Double> domain = new Range<>(0.0, 1.0);
		final LinearDistribution<Double> dist = new LinearDistribution<>(domain, 0);
		final ToDoubleFunction<Double> cdf = dist.getCDF();

		for (int i = 0; i <= 10; ++i) {
			final double x = i/10.0;
			final double y = cdf.applyAsDouble(x);
			Assert.assertEquals(x*x, y, 0.0001);
		}
	}