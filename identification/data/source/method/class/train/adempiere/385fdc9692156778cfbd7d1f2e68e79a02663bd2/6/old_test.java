	public void test_getSQLValueTSEx() throws Exception
	{
		final Timestamp target = TimeUtil.getDay(2008, 01, 01);
		//
		Timestamp result = DB.getSQLValueTSEx(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM AD_SYSTEM");
		assertEquals(target, result);
		//
		result = DB.getSQLValueTSEx(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		DBException ex = null;
		try
		{
			result = DB.getSQLValueTSEx(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM INEXISTENT_TABLE");
		}
		catch (DBException e)
		{
			ex = e;
		}
		assertNotNull("No DBException Was Throwed", ex);
	}