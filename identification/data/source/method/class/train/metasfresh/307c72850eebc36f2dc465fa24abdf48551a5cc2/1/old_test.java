@Test
	public void test_add_sameQty_ExpectConstantPercent_SimpleTest01()
	{
		final ReceiptQtyExpectation<Object> expectationForOneTransaction = ReceiptQtyExpectation.newInstance()
				.qtyPrecision(2)
				.qty("7")
				.qualityDiscountPercent("3")
				.qtyWithIssues("0.21") // = 3 * 7% = 0.21
		;
		test_add_sameQty_ExpectConstantPercent(expectationForOneTransaction);
	}