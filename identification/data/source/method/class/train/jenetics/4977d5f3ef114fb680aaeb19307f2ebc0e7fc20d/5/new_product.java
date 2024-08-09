public static byte[] flip(final byte[] data, final int index) {
		final int bytes = index >>> 3; // = index/8
		final int bits = index & 7;    // = index%8
		int d = data[bytes] & 0xFF;

		if ((d & (1 << bits)) == 0) {
			d |= (1 << bits);
		} else {
			d &= ~(1 << bits);
		}
		data[bytes] = (byte)d;

		return data;
	}