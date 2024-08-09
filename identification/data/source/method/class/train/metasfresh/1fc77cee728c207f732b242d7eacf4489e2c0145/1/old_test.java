@Test
	public void test_calculateQtyRequiredBasedOnFinishedGoodReceipt_UnderReceipt()
	{
		// Finished good
		ppOrder.setQtyOrdered(new BigDecimal("100"));
		ppOrder.setQtyDelivered(new BigDecimal("30")); // under-receipt: 30 < 100

		// Component
		ppOrderBOMLine.setPP_Order(ppOrder); // just to make sure we have the latest
		ppOrderBOMLine.setIsQtyPercentage(false);
		ppOrderBOMLine.setQtyBOM(new BigDecimal("260"));
		ppOrderBOMLine.setQtyBatch(null);
		ppOrderBOMLine.setScrap(new BigDecimal("10")); // 10%
		ppOrderBOMLine.setQtyDelivered(BigDecimal.ZERO);

		Assert.assertThat("Invalid QtyRequired projected",
				ppOrderBOMBL.calculateQtyRequiredBasedOnFinishedGoodReceipt(ppOrderBOMLine),
				// Expected: 50(finished goods received) x 260(mm/finished good) x (scrap=1 + 10/100)
				Matchers.comparesEqualTo(new BigDecimal("8580"))
				);

	}