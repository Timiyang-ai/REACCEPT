public static byte[] shiftLeft(final byte[] data, final int shift) {
		final int bytes = min(shift >>> 3, data.length);
		final int bits = shift & 7;
		
		if (bytes > 0) {
			for (int i = 0, n = data.length - bytes; i < n; ++i) {
				data[data.length - 1 - i] = data[data.length - 1 - i - bytes];
			}
			for (int i = 0; i < bytes; ++i) {
				data[i] = (byte)0;
			}
		}
		if (bits > 0 && bytes < data.length) {
			int carry = 0;
			int nextCarry = 0;
			
			for (int i = bytes; i < data.length; ++i) {
				int d = data[i] & 0xFF;
				nextCarry = (d >>> (8 - bits));
				
				d <<= bits;
				d |= carry;
				data[i] = (byte)(d & 0xFF);
							
				carry = nextCarry;
			}
		}
		
		return data;
	}