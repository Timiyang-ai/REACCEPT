	public void test_getSQLValue() throws Exception
	{
		int result = DB.getSQLValue(null, "SELECT 10 FROM DUAL");
		assertEquals(10, result);
		//
		result = DB.getSQLValue(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
		assertEquals("No value should be returned", -1, result);
		//
		result = DB.getSQLValue(null, "SELECT 10 FROM INEXISTENT_TABLE");
		assertEquals("Error should be signaled", -1, result);
	}