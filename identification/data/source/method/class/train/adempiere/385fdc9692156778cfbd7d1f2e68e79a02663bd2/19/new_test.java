	public void test_getSQLValueEx() throws Exception
	{
		int result = DB.getSQLValueEx(null, "SELECT 10 FROM DUAL");
		assertEquals(10, result);
		//
		result = DB.getSQLValue(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
		assertEquals("No value should be returned", -1, result);
		//
		DBException ex = null;
		try
		{
			result = DB.getSQLValueEx(null, "SELECT 10 FROM INEXISTENT_TABLE");
		}
		catch (DBException e)
		{
			ex = e;
		}
		assertNotNull("No DBException Was Throwed", ex);
	}