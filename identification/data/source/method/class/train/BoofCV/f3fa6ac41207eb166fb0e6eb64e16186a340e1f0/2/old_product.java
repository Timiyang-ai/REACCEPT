public QrCode alphanumeric( String alphaNumeric ) {
		byte values[] = alphanumericToValues(alphaNumeric);

		qr.mode = QrCode.Mode.ALPHANUMERIC;

		int lengthBits = getLengthBitsAlphanumeric(qr.version);

		packed.resize(lengthBits + 11*(values.length/2+1)); // predeclare memory
		packed.size = 0; // set size to 0 so that append() starts from the front

		// specify the mode
		packed.append(0b0010,4,false);

		// Specify the number of digits
		packed.append(values.length,lengthBits,false);

		// Append the digits
		int index = 0;
		while( values.length-index >= 2 ) {
			int value = values[index] * 45 + values[index+1];
			packed.append(value,11,false);
			index += 2;
		}
		if( values.length-index == 1 ) {
			int value = values[index];
			packed.append(value,6,false);
		}

		// add the terminator to the bit stream
		packed.append(0b0000,4,false);

//		packed.print();

		bitsToMessage(packed);

		return qr;
	}