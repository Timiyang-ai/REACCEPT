public SqlPlus LIKE(String column, String value, SQLlikeType type) {
		handerLike(column, value, type, false);
		return this;
	}