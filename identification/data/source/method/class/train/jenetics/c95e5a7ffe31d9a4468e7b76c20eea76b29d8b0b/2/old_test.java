	@Test(dataProvider = "aritySamples")
	public void arity(final Sample<?> sample, final int arity) {
		Assert.assertEquals(sample.arity(), arity);
	}