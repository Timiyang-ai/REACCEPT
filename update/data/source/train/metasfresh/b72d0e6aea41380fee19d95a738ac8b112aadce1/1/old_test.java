@Test
	public void test_calculatePreparationDateOrNull()
	{

		final IContextAware ctx = InterfaceWrapperHelper.getContextAware(tour);

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
		final Timestamp dateOrdered1 = toDateTimeTimestamp("27.03.2017 11:29:00.000");
		final Timestamp datePromised1 = toDateTimeTimestamp("27.03.2017 23:59:00.000");
		final Timestamp preparationDate1 = deliveryDayBL.calculatePreparationDateOrNull(ctx, true, dateOrdered1, datePromised1, bpLocation.getC_BPartner_Location_ID());
		final Timestamp expected1 = toDateTimeTimestamp("27.03.2017 11:30:00.000");

		Assert.assertEquals(preparationDate1, expected1);

		// 27.03.2017 23:59h, dateOrdered 27.03.2017 11:31h
		final Timestamp dateOrdered2 = toDateTimeTimestamp("27.03.2017 11:31:00.000");
		final Timestamp datePromised2 = toDateTimeTimestamp("27.03.2017 23:59:00.000");
		final Timestamp preparationDate2 = deliveryDayBL.calculatePreparationDateOrNull(ctx, true, dateOrdered2, datePromised2, bpLocation.getC_BPartner_Location_ID());
		final Timestamp expected2 = toDateTimeTimestamp("27.03.2017 18:30:00.000");

		Assert.assertEquals(preparationDate2, expected2);

		// 02.04.2017 23:59h, dateOrdered 27.03.2017 11:29h
		final Timestamp dateOrdered3 = toDateTimeTimestamp("27.03.2017 11:29:00.000");
		final Timestamp datePromised3 = toDateTimeTimestamp("02.04.2017 23:59:00.000");
		final Timestamp preparationDate3 = deliveryDayBL.calculatePreparationDateOrNull(ctx, true, dateOrdered3, datePromised3, bpLocation.getC_BPartner_Location_ID());
		final Timestamp expected3 = toDateTimeTimestamp("02.04.2017 11:30:00.000");

		Assert.assertEquals(preparationDate3, expected3);

	}