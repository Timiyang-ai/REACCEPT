	@Test
	public void pdf() {
		final UniformDistribution<Double> dist = new UniformDistribution<>(0.0, 10.0);
		final ToDoubleFunction<Double> pdf = dist.getPDF();

		Assert.assertEquals(pdf.applyAsDouble(0.00), 0.1);
		Assert.assertEquals(pdf.applyAsDouble(1.15), 0.1);
		Assert.assertEquals(pdf.applyAsDouble(2.24), 0.1);
		Assert.assertEquals(pdf.applyAsDouble(3.43), 0.1);
		Assert.assertEquals(pdf.applyAsDouble(4.42), 0.1);
		Assert.assertEquals(pdf.applyAsDouble(5.59), 0.1);
		Assert.assertEquals(pdf.applyAsDouble(10.0), 0.1);

		Assert.assertEquals(pdf.applyAsDouble(-0.01), 0.0);
		Assert.assertEquals(pdf.applyAsDouble(10.01), 0.0);
	}