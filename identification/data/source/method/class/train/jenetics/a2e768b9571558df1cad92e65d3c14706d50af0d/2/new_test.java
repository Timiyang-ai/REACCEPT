	@Test
	public void sameState() {
		final IntMomentStatistics ims1 = new IntMomentStatistics();
		final IntMomentStatistics ims2 = new IntMomentStatistics();

		final Random random = new Random();
		for (int i = 0; i < 100; ++i) {
			final int value = random.nextInt(1_000_000);
			ims1.accept(value);
			ims2.accept(value);

			Assert.assertTrue(ims1.sameState(ims2));
			Assert.assertTrue(ims2.sameState(ims1));
			Assert.assertTrue(ims1.sameState(ims1));
			Assert.assertTrue(ims2.sameState(ims2));
		}
	}