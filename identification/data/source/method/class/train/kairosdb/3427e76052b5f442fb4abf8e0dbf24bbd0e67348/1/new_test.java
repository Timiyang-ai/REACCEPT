	@Test(expected = NullPointerException.class)
	public void test_isNumber_nullString_invalid()
	{
		Util.isNumber(null);
	}