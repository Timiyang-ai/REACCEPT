public static boolean[] getBits(int value) {

		String zeroBitString = String.format("%0" + 8 + 'd', 0);
		String binaryString = Integer.toBinaryString(value);
		binaryString = zeroBitString.substring(binaryString.length())
				+ binaryString;

		boolean[] bits = new boolean[8];

		for (int pos = 7; pos > 0; pos--) {
			bits[7 - pos] = binaryString.substring(pos, pos + 1)
					.equalsIgnoreCase("1") ? true : false;
		}

		// bits are reverse order representing the original binary string
		// e.g. string "0001 0010" is bits[0] -> 0100 1000 <- bits[7]
		for (boolean bit : bits) {
			String b = bit == true ? "1" : "0";
		}

		return bits;
	}