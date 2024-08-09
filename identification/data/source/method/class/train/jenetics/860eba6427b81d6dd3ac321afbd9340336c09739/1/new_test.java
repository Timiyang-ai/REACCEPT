	@Test
	public void cdf() {
		final UniformDistribution<Double> dist = new UniformDistribution<>(0.0, 10.0);
		final ToDoubleFunction<Double> cdf = dist.getCDF();

		Assert.assertEquals(cdf.applyAsDouble(-9.0), 0.0);
		Assert.assertEquals(cdf.applyAsDouble(0.0), 0.0);
		Assert.assertEquals(cdf.applyAsDouble(1.0), 0.1);
		Assert.assertEquals(cdf.applyAsDouble(2.0), 0.2);
		Assert.assertEquals(cdf.applyAsDouble(3.0), 0.3);
		Assert.assertEquals(cdf.applyAsDouble(4.0), 0.4);
		Assert.assertEquals(cdf.applyAsDouble(5.0), 0.5);
		Assert.assertEquals(cdf.applyAsDouble(6.0), 0.6);
		Assert.assertEquals(cdf.applyAsDouble(7.0), 0.7);
		Assert.assertEquals(cdf.applyAsDouble(8.0), 0.8);
		Assert.assertEquals(cdf.applyAsDouble(9.0), 0.9);
		Assert.assertEquals(cdf.applyAsDouble(10.0), 1.0);
		Assert.assertEquals(cdf.applyAsDouble(19.0), 1.0);
	}