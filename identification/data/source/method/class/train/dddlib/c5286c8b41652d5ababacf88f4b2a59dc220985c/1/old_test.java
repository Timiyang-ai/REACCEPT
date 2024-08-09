@Test
	public void testGeneratePagingQueryStatement() {
		String sqlExpected = "select * from DATA_SOURCES LIMIT 0,10";
		String sql = mySqlPagingQueryDialect.generatePagingQueryStatement();
		assertEquals(sqlExpected, sql);
	}