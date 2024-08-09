	@Test
	public void increment() {
		final byte[] data = new byte[4];
		for (int i = 0; i < Short.MAX_VALUE; ++i) {
			final int value = toInt(data);
			Assert.assertEquals(value, i);
			bit.increment(data);
		}
	}