private void init(@NonNull final ShipmentScheduleWithHU candidate)
	{
		Check.assume(isEmpty(), "builder shall be empty");

		huContext = candidate.getHUContext();

		//
		// Product, ASI, UOM (retrieved from Shipment Schedule)
		product = candidate.getM_Product();
		attributeValues.addAll(candidate.getAttributeValues());
		attributesAggregationKey = candidate.getAttributesAggregationKey();
		uom = shipmentScheduleBL.getUomOfProduct(candidate.getM_ShipmentSchedule());

		//
		// Order Line Link (retrieved from current Shipment)
		orderLineId = candidate.getC_OrderLine_ID();
	}