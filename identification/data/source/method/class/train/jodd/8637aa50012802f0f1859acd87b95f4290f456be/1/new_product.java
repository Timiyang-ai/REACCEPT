public String convertColumnNameToPropertyName(final String columnName) {
		StringBuilder propertyName = new StringBuilder(columnName.length());
		int len = columnName.length();

		if (splitCamelCase) {
			boolean toUpper = false;
			for (int i = 0; i < len; i++) {
				char c = columnName.charAt(i);
				if (c == separatorChar) {
					toUpper = true;
					continue;
				}
				if (toUpper) {
					propertyName.append(Character.toUpperCase(c));
					toUpper = false;
				} else {
					propertyName.append(Character.toLowerCase(c));
				}
			}
			return propertyName.toString();
		}
		return columnName;
	}