	@Test
	public void mae() {
		final Double[] expected = new Double[100];
		final Double[] calculated = new Double[100];

		for (int i = 0; i < expected.length; ++i) {
			expected[i] = (double)i;
			calculated[i] = (double)i + 1;
		}
		Assert.assertEquals(LossFunction.mae(calculated, expected), 1.0);

		for (int i = 0; i < expected.length; ++i) {
			calculated[i] = (double)i + 2;
		}
		Assert.assertEquals(LossFunction.mae(calculated, expected), 2.0);

		for (int i = 0; i < expected.length; ++i) {
			calculated[i] = (double)i + 3;
		}
		Assert.assertEquals(LossFunction.mae(calculated, expected), 3.0);
	}