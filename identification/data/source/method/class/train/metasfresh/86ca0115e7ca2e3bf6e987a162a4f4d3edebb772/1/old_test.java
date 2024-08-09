	@Test
	public void trunc()
	{
		testTrunc(null, 10, null);
		testTrunc("", 0, "");
		testTrunc("", 10, "");
		testTrunc("1234567890", 0, "");
		testTrunc("1234567890", 1, "1");
		testTrunc("1234567890", 2, "12");
		testTrunc("1234567890", 3, "123");
		testTrunc("1234567890", 4, "1234");
		testTrunc("1234567890", 5, "12345");
		testTrunc("1234567890", 6, "123456");
		testTrunc("1234567890", 7, "1234567");
		testTrunc("1234567890", 8, "12345678");
		testTrunc("1234567890", 9, "123456789");
		testTrunc("1234567890", 10, "1234567890");
		testTrunc("1234567890", 11, "1234567890");
		testTrunc("1234567890", 12, "1234567890");
		testTrunc("1234567890", 13, "1234567890");
	}