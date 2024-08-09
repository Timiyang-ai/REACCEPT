public static void swap(
		final byte[] data, final int start, final int end,
		final byte[] otherData, final int otherStart
	) {
		for (int i = end - start; --i >= 0;) {
			final boolean temp = get(data, i + start);
			set(data, i + start, get(otherData, otherStart + i));
			set(otherData, otherStart + i, temp);
		}
	}