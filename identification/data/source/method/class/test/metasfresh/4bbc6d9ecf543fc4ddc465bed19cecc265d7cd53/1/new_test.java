@Test
	public void addQty_HavingNegativeQtyFree()
	{
		final BigDecimal qtyTotal = new BigDecimal("10");
		final BigDecimal qty = new BigDecimal("13"); // > qtyTotal
		final boolean allowNegativeCapacity = false;
		final Capacity capacityTotal = Capacity.createCapacity(qtyTotal, pTomatoId, uomEach, allowNegativeCapacity);
		final Bucket capacity = Bucket.createBucketWithCapacityAndQty(capacityTotal, qty);

		// Make sure our configuration is right
		assertCapacityLevels(capacity, "10", "13", "-3");

		// Try adding one more item
		// NOTE: because qtyFree is negative we expect nothing to be added
		addQtyAndTest(capacity, uomEach, "1", "0");
		assertCapacityLevels(capacity, "10", "13", "-3");
	}