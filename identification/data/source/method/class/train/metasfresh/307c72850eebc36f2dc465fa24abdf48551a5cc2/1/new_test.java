@Test
	public void test_add_sameQty_ExpectConstantPercent_SimpleTest01()
	{
		final ReceiptQtyExpectation<Object> expectationForOneTransaction = ReceiptQtyExpectation.newInstance()
				.qtyPrecision(2)
				.qty(StockQtyAndUOMQtys.create(new BigDecimal("8"), productId, new BigDecimal("7"), uomId)) // note that at the end of the day, we care for the uomQty, i.e. the potential catch quantity
				.qualityDiscountPercent("3")
				.qtyWithIssues(StockQtyAndUOMQtys.create(new BigDecimal("8"), productId, new BigDecimal("0.21"), uomId)) // = 3 * 7% = 0.21
		;
		test_add_sameQty_ExpectConstantPercent(expectationForOneTransaction);
	}