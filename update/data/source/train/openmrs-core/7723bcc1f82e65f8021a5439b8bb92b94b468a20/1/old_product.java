public List<List<Object>> executeSQL(String sql, boolean selectOnly) throws DAOException {
		
		// (solution for junit tests that usually use hsql
		// hsql does not like the backtick.  Replace the backtick with the hsql
		// escape character: the double quote (or nothing).
		if (HibernateUtil.isHSQLDialect(sessionFactory)) {
			sql = sql.replace("`", "");
		}
		return DatabaseUtil.executeSQLWithSession(sessionFactory.getCurrentSession(), sql, selectOnly);
	}