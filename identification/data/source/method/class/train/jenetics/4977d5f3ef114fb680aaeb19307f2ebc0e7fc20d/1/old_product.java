public static void flip(final byte[] data, final int index) {
		if (data.length == 0) {
			return;
		}
		
		final int max = data.length*8;
		if (index >= max || index < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
		
		//Reading the value.
		final int pos = data.length - index/8 - 1;
		final int bitPos = index%8;
		int d = data[pos] & 0xFF;
		final boolean value = (d & (1 << bitPos)) != 0;
		
		//Setting the value.
		if (value) {
			d = d | (1 << bitPos);
		} else {
			d = d & ~(1 << bitPos);
		}
		data[pos] = (byte)d;
	}