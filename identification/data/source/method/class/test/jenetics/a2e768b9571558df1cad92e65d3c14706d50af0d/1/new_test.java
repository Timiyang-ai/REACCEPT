	@Test
	public void sameState() {
		final MinMax<Long> mm1 = MinMax.of();
		final MinMax<Long> mm2 = MinMax.of();

		final Random random = new Random();
		for (int i = 0; i < 100; ++i) {
			final long value = random.nextInt(1_000_000);
			mm1.accept(value);
			mm2.accept(value);

			Assert.assertTrue(mm1.sameState(mm2));
			Assert.assertTrue(mm2.sameState(mm1));
			Assert.assertTrue(mm1.sameState(mm1));
			Assert.assertTrue(mm2.sameState(mm2));
		}
	}