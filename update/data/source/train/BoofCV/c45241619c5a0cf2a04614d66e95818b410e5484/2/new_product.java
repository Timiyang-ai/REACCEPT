private void encodeNumeric(byte[] numbers , int length ) {
		qr.mode = QrCode.Mode.NUMERIC;
		int lengthBits = getLengthBitsNumeric(qr.version);

		// specify the mode
		packed.append(0b0001, 4, false);

		// Specify the number of digits
		packed.append(length, lengthBits, false);

		// Append the digits
		int index = 0;
		while (length - index >= 3) {
			int value = numbers[index] * 100 + numbers[index + 1] * 10 + numbers[index + 2];
			packed.append(value, 10, false);
			index += 3;
		}
		if (length - index == 2) {
			int value = numbers[index] * 10 + numbers[index + 1];
			packed.append(value, 7, false);
		} else if (length - index == 1) {
			int value = numbers[index];
			packed.append(value, 4, false);
		}
	}