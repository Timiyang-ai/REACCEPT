@Test
	public void test_add_sameQty_ExpectConstantPercent_RandomValues()
	{
		for (int qtyPrecision = 0; qtyPrecision <= 6; qtyPrecision++)
		{
			for (int i = 1; i <= 10; i++)
			{
				final ReceiptQtyExpectation<Object> expectationForOneTransaction = randomQtyAndQualityExpectation(qtyPrecision);
				test_add_sameQty_ExpectConstantPercent(expectationForOneTransaction);
			}
		}

	}