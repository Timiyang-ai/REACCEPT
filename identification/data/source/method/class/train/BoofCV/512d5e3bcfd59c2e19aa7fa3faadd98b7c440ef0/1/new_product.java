public QrCode numeric(int[] numbers ) {
		for (int i = 0; i < numbers.length; i++) {
			if( numbers[i] < 0 || numbers[i] > 9 )
				throw new IllegalArgumentException("All numbers must have a value from 0 to 9");
		}
		qr.mode = QrCode.Mode.NUMERIC;
		int lengthBits = getLengthBitsNumeric(qr.version);

		packed.resize(lengthBits + 10*(numbers.length/3)); // predeclare memory
		packed.size = 0; // set size to 0 so that append() starts from the front

		// specify the mode
		packed.append(0b0001,4,false);

		// Specify the number of digits
		packed.append(numbers.length,lengthBits,false);

		// Append the digits
		int index = 0;
		while( numbers.length-index >= 3 ) {
			int value = numbers[index] * 100 + numbers[index+1]*10 + numbers[index+2];
			packed.append(value,10,false);
			index += 3;
		}
		if( numbers.length-index == 2 ) {
			int value = numbers[index]*10 + numbers[index+1];
			packed.append(value,7,false);
		} else if( numbers.length-index == 1 ) {
			int value = numbers[index];
			packed.append(value,4,false);
		}

		// add the terminator to the bit stream
		packed.append(0b0000,4,false);

		bitsToMessage(packed);

		return qr;
	}