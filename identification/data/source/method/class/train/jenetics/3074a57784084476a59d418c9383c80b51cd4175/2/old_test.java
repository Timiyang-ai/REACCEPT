	@Test(dataProvider = "argAtSamples")
	public void argAt(final Sample<?> sample, final Object[] result) {
		Assert.assertEquals(sample.arity(), result.length);
		for (int i = 0; i < sample.arity(); ++i) {
			Assert.assertEquals(sample.argAt(i), result[i]);
		}
	}