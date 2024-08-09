private void encodeKanji( byte []bytes , int length ) {
		qr.mode = QrCode.Mode.KANJI;

		int lengthBits = getLengthBitsKanji(qr.version);

		// specify the mode
		packed.append(0b1000, 4, false);

		// Specify the number of characters
		packed.append(length, lengthBits, false);

		for (int i = 0; i < bytes.length; i += 2) {
			int byte1 = bytes[i] & 0xFF;
			int byte2 = bytes[i + 1] & 0xFF;
			int code = (byte1 << 8) | byte2;
			int adjusted;
			if (code >= 0x8140 && code <= 0x9ffc) {
				adjusted = code - 0x8140;
			} else if (code >= 0xe040 && code <= 0xebbf) {
				adjusted = code - 0xc140;
			} else {
				throw new IllegalArgumentException("Invalid byte sequence. At " +(i / 2));
			}
			int encoded = ((adjusted >> 8) * 0xc0) + (adjusted & 0xff);
			packed.append(encoded, 13, false);
		}
	}