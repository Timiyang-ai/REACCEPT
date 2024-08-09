	@Test
	public void flip() {
		final long seed = random.seed();
		final Random random = new Random(seed);
		final byte[] data = new byte[1000];

		for (int i = 0; i < data.length; ++i) {
			data[i] = (byte)random.nextInt();
		}

		final byte[] cdata = data.clone();
		for (int i = 0; i < data.length*8; ++i) {
			io.jenetics.internal.util.bit.flip(cdata, i);
		}

		for (int i = 0; i < data.length*8; ++i) {
			Assert.assertEquals(io.jenetics.internal.util.bit.get(cdata, i), !io.jenetics.internal.util.bit.get(data, i), "Index: " + i);
		}
	}