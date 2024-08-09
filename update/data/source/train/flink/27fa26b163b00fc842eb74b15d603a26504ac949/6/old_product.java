@Override
	public void configure(Configuration parameters) {
		String delimString = parameters.getString(FORMAT_PAIR_DELIMITER, "\n");

		if (delimString == null) {
			throw new IllegalArgumentException("The delimiter not be null.");
		}

		delimiter = delimString.getBytes();
	}