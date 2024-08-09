public static int encodeFormatMessage(QrCode.ErrorCorrectionLevel level , int mask ) {
		int message = (level.value << 3) | (mask & 0xFFFFFFF7);
		message = message << 10;
		int tmp = message ^ bitPolyDivide(message, FORMAT_GENERATOR,15,5);
		return tmp ^ FORMAT_MASK;
	}