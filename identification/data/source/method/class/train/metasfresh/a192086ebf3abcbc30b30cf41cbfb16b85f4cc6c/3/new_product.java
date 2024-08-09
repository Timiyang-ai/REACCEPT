private void init(final IShipmentScheduleWithHU candidate)
	{
		Check.assume(isEmpty(), "builder shall be empty");

		huContext = candidate.getHUContext();

		//
		// Product, ASI, UOM (retrieved from Shipment Schedule)
		product = candidate.getM_Product();
		attributeSetInstanceId = candidate.getM_AttributeSetInstance_ID();
		attributesAggregationKey = candidate.getAttributesAggregationKey();
		uom = shipmentScheduleBL.getUomOfProduct(candidate.getM_ShipmentSchedule());

		//
		// Order Line Link (retrieved from current Shipment)
		orderLineId = candidate.getC_OrderLine_ID();
	}