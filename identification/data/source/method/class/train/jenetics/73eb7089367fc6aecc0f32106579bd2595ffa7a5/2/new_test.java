	@Test
	public void swap() {
		final int byteLength = 1_000;
		final int bitLength = byteLength*8;

		final byte[] seq = newByteArray(byteLength, new Random());

		for (int start = 0; start < bitLength - 3; ++start) {
			final byte[] copy = seq.clone();
			final byte[] other = newByteArray(byteLength, new Random());
			final byte[] otherCopy = other.clone();

			final int end = start + 2;
			final int otherStart = 1;

			io.jenetics.internal.util.bit.swap(seq, start, end, other, otherStart);

			for (int j = start; j < end; ++j) {
				final boolean actual = io.jenetics.internal.util.bit.get(seq, j);
				final boolean expected = io.jenetics.internal.util.bit.get(otherCopy, j + otherStart - start);
				Assert.assertEquals(actual, expected);
			}
			for (int j = 0; j < (end - start); ++j) {
				final boolean actual = io.jenetics.internal.util.bit.get(other, j + otherStart);
				final boolean expected = io.jenetics.internal.util.bit.get(copy, j + start);
				Assert.assertEquals(actual, expected);
			}
		}
	}