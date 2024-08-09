private static byte[] toByteArray(final BitSet bits, final int length) {
		final byte[] bytes = bit.newArray(length);
		for (int i = 0; i < length; ++i) {
			if (bits.get(i)) {
				bit.set(bytes, i);
			}
		}
		return bytes;
	}