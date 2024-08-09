	@Test
	public void mse() {
		final Double[] expected = new Double[100];
		final Double[] calculated = new Double[100];

		for (int i = 0; i < expected.length; ++i) {
			expected[i] = (double)i;
			calculated[i] = (double)i + 1;
		}
		Assert.assertEquals(LossFunction.mse(calculated, expected), 1.0);

		for (int i = 0; i < expected.length; ++i) {
			calculated[i] = (double)i + 2;
		}
		Assert.assertEquals(LossFunction.mse(calculated, expected), 4.0);

		for (int i = 0; i < expected.length; ++i) {
			calculated[i] = (double)i + 3;
		}
		Assert.assertEquals(LossFunction.mse(calculated, expected), 9.0);
	}