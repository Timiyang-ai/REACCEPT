@Deprecated
	public static byte[] complement(final byte[] data) {
		return increment(invert(data));
	}