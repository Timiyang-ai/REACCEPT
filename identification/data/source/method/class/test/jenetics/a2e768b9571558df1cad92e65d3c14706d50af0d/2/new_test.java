	@Test
	public void sameState() {
		final LongMomentStatistics ms1 = new LongMomentStatistics();
		final LongMomentStatistics ms2 = new LongMomentStatistics();

		final Random random = new Random();
		for (int i = 0; i < 100; ++i) {
			final long value = random.nextInt(1_000_000);
			ms1.accept(value);
			ms2.accept(value);

			Assert.assertTrue(ms1.sameState(ms2));
			Assert.assertTrue(ms2.sameState(ms1));
			Assert.assertTrue(ms1.sameState(ms1));
			Assert.assertTrue(ms2.sameState(ms2));
		}
	}