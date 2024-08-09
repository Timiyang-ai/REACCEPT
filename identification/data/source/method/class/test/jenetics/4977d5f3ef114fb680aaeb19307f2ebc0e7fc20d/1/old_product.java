public static byte[] flip(final byte[] data, final int index) {
		if (data.length == 0) {
			return data;
		}
		
		final int max = data.length*8;
		if (index >= max || index < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
		
		final int bytes = index/8;
		final int bits = index%8;
		int d = data[bytes] & 0xFF;
		
		if ((d & (1 << bits)) == 0) {
			d = d | (1 << bits);
		} else {
			d = d & ~(1 << bits);
		}
		data[bytes] = (byte)d;
		
		return data;
	}