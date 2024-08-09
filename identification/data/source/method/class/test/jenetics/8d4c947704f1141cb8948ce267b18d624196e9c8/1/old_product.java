public static byte[] increment(final byte[] data) {
		if (data.length == 0) {
			return data;
		}
		
		int d = 0;
		int pos = data.length - 1;
		do {
			d = data[pos] & 0xFF;
			++d;
			data[pos] = (byte)d;
			--pos;
		} while (pos >= 0 && data[pos + 1] == 0);
		
		return data;
	}