	public void test_getSQLValueTS() throws Exception
	{
		final Timestamp target = TimeUtil.getDay(2008, 01, 01);
		//
		Timestamp result = DB.getSQLValueTS(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM DUAL");
		assertEquals(target, result);
		//
		result = DB.getSQLValueTS(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		result = DB.getSQLValueTS(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM INEXISTENT_TABLE");
		assertNull("Error should be signaled", result);
	}