static String fixWindowsBug(String input, String version) {
		if (IS_WINDOWS && version.equals("1.1")) {
			int firstImport = input.indexOf("\nimport ");
			if (firstImport == 0) {
				return input;
			} else if (firstImport > 0) {
				int numToTrim = 0;
				char prevChar;
				do {
					++numToTrim;
					prevChar = input.charAt(firstImport - numToTrim);
				} while (Character.isWhitespace(prevChar) && (firstImport - numToTrim) > 0);
				if (firstImport - numToTrim == 0) {
					// import was the very first line, and we'd like to maintain a one-line gap
					++numToTrim;
				} else if (prevChar == ';' || prevChar == '/') {
					// import came after either license or a package declaration
					--numToTrim;
				}
				if (numToTrim > 0) {
					return input.substring(0, firstImport - numToTrim + 2) + input.substring(firstImport + 1);
				}
			}
		}
		return input;
	}