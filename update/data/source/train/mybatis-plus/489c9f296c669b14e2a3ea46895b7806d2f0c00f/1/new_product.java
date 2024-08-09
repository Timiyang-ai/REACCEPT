public SqlPlus LIKE(String column, String value, SqlLike type) {
		handerLike(column, value, type, false);
		return this;
	}