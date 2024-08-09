	@Test(expected = NullPointerException.class)
	public void test_buildSqlList_null_paramsOut()
	{
		final List<Object> paramsOut = null;
		DB.buildSqlList(new ArrayList<>(), paramsOut);
	}