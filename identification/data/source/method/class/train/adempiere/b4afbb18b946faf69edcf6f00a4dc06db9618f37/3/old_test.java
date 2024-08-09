	public void test_getSQLValueString() throws Exception
	{
		String result = DB.getSQLValueString(null, "SELECT 'string' FROM DUAL");
		assertEquals("string", result);
		//
		result = DB.getSQLValueString(null, "SELECT 'string' FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		result = DB.getSQLValueString(null, "SELECT 'string' FROM INEXISTENT_TABLE");
		assertNull("Error should be signaled", result);
	}