public static byte[] increment(final byte[] data) {
		for (int i = 0; i < data.length && (data[i] += 1) == 0; ++i);
		return data;
	}