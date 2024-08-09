	@Test(dataProvider = "shiftBits")
	public void shiftLeft(final Integer shift, final Integer bytes) {
		final long seed = System.currentTimeMillis();
		final Random random = new Random(seed);
		final byte[] data = new byte[bytes];

		for (int i = 0; i < data.length*8; ++i) {
			io.jenetics.internal.util.bit.set(data, i, random.nextBoolean());
		}

		io.jenetics.internal.util.bit.shiftLeft(data, shift);

		random.setSeed(seed);
		for (int i = 0; i < shift; ++i) {
			Assert.assertEquals(io.jenetics.internal.util.bit.get(data, i), false);
		}
		for (int i = shift, n = data.length*8; i < n; ++i) {
			Assert.assertEquals(io.jenetics.internal.util.bit.get(data, i), random.nextBoolean(), "Index: " + i);
		}
	}