	@Test
	public void pdf() {
		final Range<Double> domain = new Range<>(0.0, 1.0);
		final LinearDistribution<Double> dist = new LinearDistribution<>(domain, 0);
		final ToDoubleFunction<Double> pdf = dist.getPDF();

		for (int i = 0; i <= 10; ++i) {
			final double x = i/10.0;
			Assert.assertEquals(x*2, pdf.applyAsDouble(x), 0.00001);
		}
	}