public QrCode numeric(String message ) {
		int[] numbers = new int[ message.length() ];

		for (int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);
			int values = c - '0';
			if( values < 0 || values > 9 )
				throw new RuntimeException("Expected each character to be a number from 0 to 9");
			numbers[i] = values;
		}
		return numeric(numbers);
	}