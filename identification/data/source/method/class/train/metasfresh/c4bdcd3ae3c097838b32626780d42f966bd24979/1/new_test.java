@Test
	public void test_retrievePickedNotDeliveredRecords()
	{
		final I_M_ShipmentSchedule ss = createM_ShipmentSchedule();
		final I_M_ShipmentSchedule_QtyPicked qp1 = createShipmentScheduleQtyPickedRecord(ss, 0);
		final I_M_ShipmentSchedule_QtyPicked qp2 = createShipmentScheduleQtyPickedRecord(ss, 1);
		final I_M_ShipmentSchedule_QtyPicked qp3 = createShipmentScheduleQtyPickedRecord(ss, 0);
		final I_M_ShipmentSchedule_QtyPicked qp4 = createShipmentScheduleQtyPickedRecord(ss, 2);

		Assert.assertEquals(
				"Expected picked but not delivered",
				Arrays.asList(qp1, qp3),
				dao.retrieveNotOnShipmentLineRecords(ss, I_M_ShipmentSchedule_QtyPicked.class));

		Assert.assertEquals(
				"Expected picked AND delivered",
				Arrays.asList(qp2, qp4),
				dao.retrieveOnShipmentLineRecordsQuery(ss).create().list());

	}