private static String fixWindowsBug(String input) {
		if (input.startsWith("\n\n")) {
			return input.substring(1);
		} else {
			return input;
		}
	}