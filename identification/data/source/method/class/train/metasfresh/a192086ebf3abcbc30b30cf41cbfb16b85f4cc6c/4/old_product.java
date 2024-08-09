private void init(@NonNull final ShipmentScheduleWithHU candidate)
	{
		Check.assume(isEmpty(), "builder shall be empty");

		huContext = candidate.getHUContext();

		//
		// Product, ASI, UOM (retrieved from Shipment Schedule)
		productId = candidate.getProductId();
		attributeValues.addAll(candidate.getAttributeValues());
		attributesAggregationKey = candidate.getAttributesAggregationKey();

		qtyEntered = Quantity.zero(candidate.getUOM());

		final I_C_UOM stockingUOM = productBL.getStockUOM(productId);
		movementQty = Quantity.zero(stockingUOM);

		final Optional<I_C_UOM> catchUOM = productBL.getCatchUOM(productId);
		if (catchUOM.isPresent())
		{
			catchQty = Quantity.zero(catchUOM.get());
		}

		//
		// Order Line Link (retrieved from current Shipment)
		orderLineId = candidate.getOrderLineId();
	}