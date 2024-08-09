	@Test(dataProvider = "resultSamples")
	public void result(final Sample<?> sample, final Object result) {
		Assert.assertEquals(sample.result(), result);
	}