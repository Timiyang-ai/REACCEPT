public static boolean[] getBits(int value) {

		boolean[] bits = new boolean[8];
		
		for (int i = 0; i < 8; i++) {
			bits[i] = (((value>>i) & 0x1) == 1);
		}

		return bits;
	}