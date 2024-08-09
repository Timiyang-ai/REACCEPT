public Order saveOrder(Order order) throws APIException {
		//TODO reject existing orders
		discontinueExistingOrdersIfNecessary(order);
		
		return saveOrderInternal(order);
	}