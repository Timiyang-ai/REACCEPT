	static String convertColumnNameToPropertyName(String columnName) {
		ColumnNamingStrategy columnNamingStrategy = new ColumnNamingStrategy();
		return columnNamingStrategy.convertColumnNameToPropertyName(columnName);
	}