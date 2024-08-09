	@Test(dataProvider = "shiftBits")
	public void shiftRight(final Integer shift, final Integer bytes) {
		final long seed = System.currentTimeMillis();
		final Random random = new Random(seed);
		final byte[] data = new byte[bytes];

		for (int i = 0; i < data.length*8; ++i) {
			io.jenetics.internal.util.bit.set(data, i, random.nextBoolean());
		}

		io.jenetics.internal.util.bit.shiftRight(data, shift);

		random.setSeed(seed);
		for (int i = 0; i < shift; ++i) {
			random.nextBoolean();
			Assert.assertEquals(io.jenetics.internal.util.bit.get(data, data.length*8 - 1 - i), false);
		}
		for (int i = 0, n = data.length*8 - shift; i < n; ++i) {
			Assert.assertEquals(io.jenetics.internal.util.bit.get(data, i), random.nextBoolean(), "Index: " + i);
		}
	}