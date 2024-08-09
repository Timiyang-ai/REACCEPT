	@Test
	public void count() {
		for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; ++i) {
			final byte value = (byte)i;

			Assert.assertEquals(io.jenetics.internal.util.bit.count(value), count(value));
		}
	}