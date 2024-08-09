public static int bitPolyModulus(int data , int generator , int totalBits, int dataBits) {
		int errorBits = totalBits-dataBits;
		for (int i = dataBits-1; i >= 0; i--) {
			if( (data & (1 << (i+errorBits))) != 0 ) {
				data ^= generator << i;
			}
		}
		return data;
	}