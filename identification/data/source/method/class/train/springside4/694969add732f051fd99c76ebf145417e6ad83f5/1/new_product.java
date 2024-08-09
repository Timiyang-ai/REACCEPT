public static String toCsvString(Object... elements) {
		StringBuilder line = new StringBuilder();
		int last = elements.length - 1;
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == null) {
				if (i != last) {
					line.append(FIELD_SEPARATOR);
				}
				continue;
			}
			String field = elements[i].toString();

			// check for special cases
			int ndx = field.indexOf(FIELD_SEPARATOR);
			if (ndx == -1) {
				ndx = field.indexOf(FIELD_QUOTE);
			}
			if (ndx == -1 && (field.startsWith(SPACE) || field.endsWith(SPACE))) {
				ndx = 1;
			}
			if (ndx == -1) {
				ndx = StringUtils.indexOf(field, SPECIAL_CHARS);
			}

			// add field
			if (ndx != -1) {
				line.append(FIELD_QUOTE);
			}
			field = StringUtils.replace(field, QUOTE, DOUBLE_QUOTE);
			line.append(field);
			if (ndx != -1) {
				line.append(FIELD_QUOTE);
			}

			// last
			if (i != last) {
				line.append(FIELD_SEPARATOR);
			}
		}
		return line.toString();
	}