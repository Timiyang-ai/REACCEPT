@Test
	public void test_calculatePreparationDateOrNull()
	{
		final IContextAware ctx = InterfaceWrapperHelper.getContextAware(tour);
		final BPartnerLocationId bpartnerLocationId = BPartnerLocationId.ofRepoId(bpLocation.getC_BPartner_ID(), bpLocation.getC_BPartner_Location_ID());

		createDeliveryDay("27.03.2017 11:30:00.000", 4);
		createDeliveryDay("28.03.2017 11:30:00.000", 4);
		createDeliveryDay("29.03.2017 11:30:00.000", 4);
		createDeliveryDay("30.03.2017 11:30:00.000", 4);
		createDeliveryDay("31.03.2017 11:30:00.000", 4);
		createDeliveryDay("01.04.2017 11:30:00.000", 4);
		createDeliveryDay("02.04.2017 11:30:00.000", 4);

		createDeliveryDay("27.03.2017 18:30:00.000", 4);
		createDeliveryDay("28.03.2017 18:30:00.000", 4);
		createDeliveryDay("29.03.2017 18:30:00.000", 4);
		createDeliveryDay("30.03.2017 18:30:00.000", 4);
		createDeliveryDay("31.03.2017 18:30:00.000", 4);
		createDeliveryDay("01.04.2017 18:30:00.000", 4);
		createDeliveryDay("02.04.2017 18:30:00.000", 4);

		// 27.03.2017 23:59h, dateOrdered 27.03.2017 11:29h
		final ZonedDateTime dateOrdered1 = toZonedDateTime("27.03.2017 11:29:00.000");
		final ZonedDateTime datePromised1 = toZonedDateTime("27.03.2017 23:59:00.000");
		final ZonedDateTime preparationDate1 = deliveryDayBL.calculatePreparationDateOrNull(ctx, SOTrx.SALES, dateOrdered1, datePromised1, bpartnerLocationId);
		final ZonedDateTime expected1 = toZonedDateTime("27.03.2017 11:30:00.000");

		Assert.assertEquals(preparationDate1, expected1);

		// 27.03.2017 23:59h, dateOrdered 27.03.2017 11:31h
		final ZonedDateTime dateOrdered2 = toZonedDateTime("27.03.2017 11:31:00.000");
		final ZonedDateTime datePromised2 = toZonedDateTime("27.03.2017 23:59:00.000");
		final ZonedDateTime preparationDate2 = deliveryDayBL.calculatePreparationDateOrNull(ctx, SOTrx.SALES, dateOrdered2, datePromised2, bpartnerLocationId);
		final ZonedDateTime expected2 = toZonedDateTime("27.03.2017 18:30:00.000");

		Assert.assertEquals(preparationDate2, expected2);

		// 02.04.2017 23:59h, dateOrdered 27.03.2017 11:29h
		final ZonedDateTime dateOrdered3 = toZonedDateTime("27.03.2017 11:29:00.000");
		final ZonedDateTime datePromised3 = toZonedDateTime("02.04.2017 23:59:00.000");
		final ZonedDateTime preparationDate3 = deliveryDayBL.calculatePreparationDateOrNull(ctx, SOTrx.SALES, dateOrdered3, datePromised3, bpartnerLocationId);
		final ZonedDateTime expected3 = toZonedDateTime("02.04.2017 11:30:00.000");

		Assert.assertEquals(preparationDate3, expected3);

	}