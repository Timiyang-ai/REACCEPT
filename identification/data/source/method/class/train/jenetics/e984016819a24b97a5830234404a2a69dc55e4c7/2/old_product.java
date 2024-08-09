public static byte[] newByteArray(final int length, final double p) {
		final byte[] bytes = newByteArray(length);

		final IndexStream stream = IndexStream.Random(length, p);
		for (int i = stream.next(); i != -1; i = stream.next()) {
			bytes[i >>> 3] |= 1 << (i & 7);
		}

		return bytes;
	}