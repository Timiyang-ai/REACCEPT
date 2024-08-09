public QrCodeEncoder alphanumeric(String alphaNumeric) {
		byte values[] = alphanumericToValues(alphaNumeric);

		qr.mode = QrCode.Mode.ALPHANUMERIC;

		int lengthBits = getLengthBitsAlphanumeric(qr.version);

		// specify the mode
		packed.append(0b0010, 4, false);

		// Specify the number of digits
		packed.append(values.length, lengthBits, false);

		// Append the digits
		int index = 0;
		while (values.length - index >= 2) {
			int value = values[index] * 45 + values[index + 1];
			packed.append(value, 11, false);
			index += 2;
		}
		if (values.length - index == 1) {
			int value = values[index];
			packed.append(value, 6, false);
		}

		return this;
	}