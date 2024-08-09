public Order saveOrder(Order order) throws APIException {
		if (order.getOrderId() != null) {
			throw new APIException("Cannot edit an existing order, see OrderService.reviseOrder");
		}

		discontinueExistingOrdersIfNecessary(order);
		
		return saveOrderInternal(order);
	}