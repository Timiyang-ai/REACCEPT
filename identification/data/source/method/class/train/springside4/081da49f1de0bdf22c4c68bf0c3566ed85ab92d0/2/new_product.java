public static String[] fromCsvString(String line) {
		List<String> row = new ArrayList<String>();

		boolean inQuotedField = false;
		int fieldStart = 0;

		final int len = line.length();
		for (int i = 0; i < len; i++) {
			char c = line.charAt(i);
			if (c == FIELD_SEPARATOR) {
				if (!inQuotedField) { // ignore we are quoting
					addField(row, line, fieldStart, i, inQuotedField);
					fieldStart = i + 1;
				}
			} else if (c == FIELD_QUOTE) {
				if (inQuotedField) {
					if (i + 1 == len || line.charAt(i + 1) == FIELD_SEPARATOR) { // we are already quoting - peek to see
																					// if this is the end of the field
						addField(row, line, fieldStart, i, inQuotedField);
						fieldStart = i + 2;
						i++; // and skip the comma
						inQuotedField = false;
					}
				} else if (fieldStart == i) {
					inQuotedField = true; // this is a beginning of a quote
					fieldStart++; // move field start
				}
			}
		}
		// add last field - but only if string was not empty
		if (len > 0 && fieldStart <= len) {
			addField(row, line, fieldStart, len, inQuotedField);
		}
		return row.toArray(new String[row.size()]);
	}