private void init(final IShipmentScheduleWithHU candidate)
	{
		Check.assume(isEmpty(), "builder shall be empty");

		final I_M_ShipmentSchedule sched = candidate.getM_ShipmentSchedule();

		//
		// Product, ASI, UOM (retrieved from Shipment Schedule)
		product = sched.getM_Product();
		attributeSetInstanceId = sched.getM_AttributeSetInstance_ID();
		if (attributeSetInstanceId <= 0)
		{
			attributeSetInstanceId = 0;
		}
		uom = shipmentScheduleBL.getC_UOM_For_ShipmentLine(sched);

		//
		// Order Line Link (retrieved from current Shipment)
		orderLineId = sched.getC_OrderLine_ID();
	}