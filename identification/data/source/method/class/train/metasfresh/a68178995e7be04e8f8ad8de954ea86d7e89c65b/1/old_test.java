@Test
	public void test_allocate_to_LUs()
	{
		setupContext(100); // qtyToDeliver=100

		//
		// Create LUs for Qty=30 and allocate to them
		{
			final List<I_M_HU> luHUs = createLUs(helper, huDefPalet, huDefIFCO, 30);
			assertThat(luHUs).hasSize(1);

			final PackingItemsMap packingItems = PackingItemsMap.ofUnpackedItem(itemToPack);
			HU2PackingItemsAllocator.builder()
					.itemToPack(itemToPack)
					.packingItems(packingItems)
					.fromHUs(luHUs)
					.allocate();

			// Validate
			assertThat("Invalid itemToPack - Qty", itemToPack.getQtySum().getAsBigDecimal(), comparesEqualTo(BigDecimal.valueOf(100 - 30)));
			assertTrue("We shall have unpacked items", packingItems.hasUnpackedItems());
			assertTrue("We shall have packed items", packingItems.hasPackedItems());

			new ShipmentScheduleQtyPickedExpectations()
					.shipmentSchedule(shipmentSchedule)
					.qtyPicked("30")
					.assertExpected("shipment schedule");

			assertValidShipmentScheduleLUAssignments(luHUs);
			assertThat(POJOLookupMap.get().getRecords(I_M_ShipmentSchedule_QtyPicked.class)).hasSize(1); // we expect one record
		}

		//
		// Create LUs for Qty=60 and allocate to them
		{
			final List<I_M_HU> luHUs = createLUs(helper, huDefPalet, huDefIFCO, 60);
			assertThat(luHUs).hasSize(2);

			final PackingItemsMap packingItems = PackingItemsMap.ofUnpackedItem(itemToPack);
			HU2PackingItemsAllocator.builder()
					.itemToPack(itemToPack)
					.packingItems(packingItems)
					.fromHUs(luHUs)
					.allocate();

			// Validate
			assertThat("Invalid itemToPack - Qty", itemToPack.getQtySum().getAsBigDecimal(), comparesEqualTo(BigDecimal.valueOf(100 - 30 - 60)));
			assertTrue("We shall have unpacked items", packingItems.hasUnpackedItems());
			assertTrue("We shall have packed items", packingItems.hasPackedItems());

			new ShipmentScheduleQtyPickedExpectations()
					.shipmentSchedule(shipmentSchedule)
					.qtyPicked("90")
					.assertExpected("shipment schedule");

			assertValidShipmentScheduleLUAssignments(luHUs);
			assertThat(POJOLookupMap.get().getRecords(I_M_ShipmentSchedule_QtyPicked.class)).hasSize(1 + 2);
		}

		//
		// Create LUs for Qty=10 and allocate to them
		{
			final List<I_M_HU> luHUs = createLUs(helper, huDefPalet, huDefIFCO, 10);
			assertThat(luHUs).hasSize(1);

			final PackingItemsMap packingItems = PackingItemsMap.ofUnpackedItem(itemToPack);
			HU2PackingItemsAllocator.builder()
					.itemToPack(itemToPack)
					.packingItems(packingItems)
					.fromHUs(luHUs)
					.allocate();

			// Validate
			assertThat("Invalid itemToPack - Qty", itemToPack.getQtySum().getAsBigDecimal(), comparesEqualTo(BigDecimal.valueOf(0)));
			assertFalse("We shall NOT have unpacked items", packingItems.hasUnpackedItems());
			assertTrue("We shall have packed items", packingItems.hasPackedItems());

			new ShipmentScheduleQtyPickedExpectations()
					.shipmentSchedule(shipmentSchedule)
					.qtyPicked("100")
					.assertExpected("shipment schedule");
			assertValidShipmentScheduleLUAssignments(luHUs);
			assertThat(POJOLookupMap.get().getRecords(I_M_ShipmentSchedule_QtyPicked.class)).hasSize(1 + 2 + 1);
		}
	}