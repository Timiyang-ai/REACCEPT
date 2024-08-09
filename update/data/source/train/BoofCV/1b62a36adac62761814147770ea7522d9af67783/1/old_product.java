public static int encodeFormatBits(QrCode.ErrorCorrectionLevel level , int mask ) {
		int message = (level.value << 3) | (mask & 0xFFFFFFF7);
		message = message << 10;
		return message ^ bitPolyModulus(message, FORMAT_GENERATOR,15,5);
	}