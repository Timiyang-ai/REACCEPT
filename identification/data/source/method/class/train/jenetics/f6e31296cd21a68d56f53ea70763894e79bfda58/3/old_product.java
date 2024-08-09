public static byte[] shiftLeft(final byte[] data, final int bits) {
		if (bits <= 0) {
			return data;
		}
		
		int d = 0;
		if (data.length == 1) {
			if (bits <= 8) {
				d = data[0] & 0xFF;
				d <<= bits;
				data[0] = (byte)d;
			} else {
				data[0] = 0;
			}
		} else if (data.length > 1) {
			int carry = 0;
			
			if (bits < 8) {
				for (int i = 0; i < data.length - 1; ++i) {
					carry = data[i + 1] & (1 >>> (8 - bits));
					
					d = data[i] & 0xFF;
					d <<= bits;
					d |= carry;
	
					data[i] = (byte)d;
				}
				
				d = data[data.length - 1] & 0xFF;
				d <<= bits;
	
				data[data.length - 1] = (byte)d ;
			} else {
				for (int i = 0; i < data.length - 1; ++i) {
					data[i] = data[i + 1];
				}
				data[data.length - 1] = 0;
				shiftLeft(data, bits - 8);
			}
		}
		
		return data;
	}