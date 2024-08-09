	@Test
	public void sameState() {
		final Quantile q1 = Quantile.median();
		final Quantile q2 = Quantile.median();

		final Random random = new Random();
		for (int i = 0; i < 100; ++i) {
			final double value = random.nextInt(1_000_000);
			q1.accept(value);
			q2.accept(value);

			Assert.assertTrue(q1.sameState(q2));
			Assert.assertTrue(q2.sameState(q1));
			Assert.assertTrue(q1.sameState(q1));
			Assert.assertTrue(q2.sameState(q2));
		}
	}