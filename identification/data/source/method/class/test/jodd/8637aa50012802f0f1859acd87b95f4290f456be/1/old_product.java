public String convertPropertyNameToColumnName(String propertyName) {
		StringBuilder tableName = new StringBuilder(propertyName.length() * 2);

		if (splitCamelCase) {
			String convertedTableName = StringUtil.fromCamelCase(propertyName, separatorChar);
			tableName.append(convertedTableName);
		} else {
			tableName.append(propertyName);
		}

		if (changeCase == false) {
			return tableName.toString();
		}
		return uppercase ?
				toUppercase(tableName).toString() :
				toLowercase(tableName).toString();

	}