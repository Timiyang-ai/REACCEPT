	@Test
	public void subtractFromBase()
	{
		test_subtractFromBase(0, 0, 0);
		test_subtractFromBase(0, 50, 0);
		test_subtractFromBase(0, 100, 0);

		test_subtractFromBase(100, 0, 100);
		test_subtractFromBase(100, 45, 55);
		test_subtractFromBase(100, 100, 0);
	}