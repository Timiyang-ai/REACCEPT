	public void test_getSQLValueStringEx() throws Exception
	{
		String result = DB.getSQLValueStringEx(null, "SELECT 'string' FROM DUAL");
		assertEquals("string", result);
		//
		result = DB.getSQLValueStringEx(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		DBException ex = null;
		try
		{
			result = DB.getSQLValueStringEx(null, "SELECT 'string' FROM INEXISTENT_TABLE");
		}
		catch (DBException e)
		{
			ex = e;
		}
		assertNotNull("No DBException Was Throwed", ex);
	}