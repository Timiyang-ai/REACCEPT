	@Test
	public void complement() {
		final Random random = new Random(io.jenetics.internal.math.random.seed());
		final byte[] data = new byte[20];
		random.nextBytes(data);

		final byte[] cdata = io.jenetics.internal.util.bit.complement(data.clone());
		Assert.assertFalse(Arrays.equals(data, cdata));
		Assert.assertTrue(Arrays.equals(data, io.jenetics.internal.util.bit.complement(cdata)));
	}