public static byte[] shiftRight(final byte[] data, final int bits) {
		if (bits <= 0) {
			return data;
		}
		
		
		int d = 0;
		if (data.length == 1) {
			if (bits <= 8) {
				d = data[0] & 0xFF;
				d >>>= bits;
				data[0] = (byte)d;
			} else {
				data[0] = 0;
			}
		} else if (data.length > 1) {
			int carry = 0;
			
			if (bits < 8) {
				for (int i = data.length - 1; i > 0; --i) {
					carry = data[i - 1] & (1 << (bits - 1));
					carry = carry << (8 - bits);
					
					d = data[i] & 0xFF;
					d >>>= bits;
					d |= carry;
	
					data[i] = (byte)d;
				}
				
				d = data[0] & 0xFF;
				d >>>= bits;
	
				data[0] = (byte)d ;
			} else {
				for (int i = data.length - 1; i > 0; --i) {
					data[i] = data[i - 1];
				}
				data[0] = 0;
				shiftRight(data, bits - 8);
			}
		}
		
		return data;
	}