	@Test
	public void invert() {
		final long seed = System.currentTimeMillis();
		final Random random = new Random(seed);
		final byte[] data = new byte[1000];

		for (int i = 0; i < data.length*8; ++i) {
			io.jenetics.internal.util.bit.set(data, i, random.nextBoolean());
		}

		final byte[] cdata = data.clone();
		io.jenetics.internal.util.bit.invert(cdata);

		for (int i = 0; i < data.length*8; ++i) {
			Assert.assertEquals(io.jenetics.internal.util.bit.get(cdata, i), !io.jenetics.internal.util.bit.get(data, i), "Index: " + i);
		}
	}