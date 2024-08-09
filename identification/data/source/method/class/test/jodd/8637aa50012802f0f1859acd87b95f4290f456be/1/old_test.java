	static String convertPropertyNameToColumnName(String propertyName, boolean toUpperCase) {
		ColumnNamingStrategy columnNamingStrategy = new ColumnNamingStrategy();
		columnNamingStrategy.setUppercase(toUpperCase);
		return columnNamingStrategy.convertPropertyNameToColumnName(propertyName);
	}