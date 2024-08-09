public static byte[] increment(final byte[] data) {
		for (int i = 0; i < data.length && (data[i] += (byte)1) == 0; ++i);
		return data;
	}