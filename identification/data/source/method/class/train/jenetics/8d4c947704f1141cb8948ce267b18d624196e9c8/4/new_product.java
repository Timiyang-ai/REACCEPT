public static byte[] increment(final byte[] data) {
		boolean carry = true;
		for (int i = 0; i < data.length && carry; ++i) {
			data[i] = (byte)(data[i] + 1);
			carry = data[i] > 0xFF;
		}

		return data;
	}