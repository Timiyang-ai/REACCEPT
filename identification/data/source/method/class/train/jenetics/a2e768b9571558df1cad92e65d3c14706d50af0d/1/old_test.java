	@Test
	public void sameState() {
		final DoubleMomentStatistics dms1 = new DoubleMomentStatistics();
		final DoubleMomentStatistics dms2 = new DoubleMomentStatistics();

		final Random random = new Random();
		for (int i = 0; i < 100; ++i) {
			final double value = random.nextDouble();
			dms1.accept(value);
			dms2.accept(value);

			Assert.assertTrue(dms1.sameState(dms2));
			Assert.assertTrue(dms2.sameState(dms1));
			Assert.assertTrue(dms1.sameState(dms1));
			Assert.assertTrue(dms2.sameState(dms2));
		}
	}