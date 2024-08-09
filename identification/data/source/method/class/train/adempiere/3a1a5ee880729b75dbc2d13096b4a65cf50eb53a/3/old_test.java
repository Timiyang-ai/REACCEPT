	public void test_getSQLValueBD() throws Exception
	{
		BigDecimal result = DB.getSQLValueBD(null, "SELECT 10 FROM DUAL");
		assertEquals(BigDecimal.TEN, result);
		//
		result = DB.getSQLValueBD(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		result = DB.getSQLValueBD(null, "SELECT 10 FROM INEXISTENT_TABLE");
		assertNull("Error should be signaled", result);
	}