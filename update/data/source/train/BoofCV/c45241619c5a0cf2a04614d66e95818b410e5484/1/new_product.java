private void encodeAlphanumeric( byte[] numbers , int length ) {
		qr.mode = QrCode.Mode.ALPHANUMERIC;

		int lengthBits = getLengthBitsAlphanumeric(qr.version);

		// specify the mode
		packed.append(0b0010, 4, false);

		// Specify the number of digits
		packed.append(length, lengthBits, false);

		// Append the digits
		int index = 0;
		while (length - index >= 2) {
			int value = numbers[index] * 45 + numbers[index + 1];
			packed.append(value, 11, false);
			index += 2;
		}
		if (length - index == 1) {
			int value = numbers[index];
			packed.append(value, 6, false);
		}
	}