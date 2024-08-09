@Authorized(OpenmrsConstants.PRIV_SQL_LEVEL_ACCESS)
	public List<List<Object>> executeSQL(String sql, boolean selectOnly) throws APIException;