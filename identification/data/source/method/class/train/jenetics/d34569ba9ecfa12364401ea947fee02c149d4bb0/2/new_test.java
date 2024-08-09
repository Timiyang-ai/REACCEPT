	@Test
	public void subset() {
		final Random random = new Random();

		for (int i = 1; i <= 1000; ++i) {
			int[] sub = new int[i];
			comb.subset(1000, sub, random);

			Assert.assertTrue(isSortedAndUnique(sub), "K: " + i);
		}
	}