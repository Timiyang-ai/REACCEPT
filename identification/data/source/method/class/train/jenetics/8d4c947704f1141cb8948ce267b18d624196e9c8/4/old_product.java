public static byte[] increment(final byte[] data) {
		boolean carry = true;
		int index = 0;

		while (index < data.length && carry) {
			int d = data[index] & 0xFF;
			++d;
			data[index++] = (byte)d;

			carry = d > 0xFF;
		}

		return data;
	}