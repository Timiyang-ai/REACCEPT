public String convertPropertyNameToColumnName(final String propertyName) {
		StringBuilder tableName = new StringBuilder(propertyName.length() * 2);

		if (splitCamelCase) {
			String convertedTableName = Format.fromCamelCase(propertyName, separatorChar);
			tableName.append(convertedTableName);
		} else {
			tableName.append(propertyName);
		}

		if (!changeCase) {
			return tableName.toString();
		}
		return uppercase ?
				toUppercase(tableName).toString() :
				toLowercase(tableName).toString();

	}