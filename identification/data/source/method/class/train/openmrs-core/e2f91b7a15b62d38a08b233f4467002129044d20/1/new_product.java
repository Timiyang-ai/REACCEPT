public Order saveOrder(Order order, OrderContext orderContext) throws APIException {
		if (order.getOrderId() != null) {
			throw new APIException("Cannot edit an existing order, you need to revise it instead");
		}
		
		if (order.getOrderType() == null) {
			OrderType orderType = getOrderTypeByConcept(order.getConcept());
			if (orderType == null) {
				throw new APIException("No order type matches the concept class");
			}
			order.setOrderType(orderType);
		}
		
		if (Order.Action.REVISE.equals(order.getAction())) {
			Order previousOrder = order.getPreviousOrder();
			if (previousOrder == null) {
				throw new APIException("Previous Order is required for a revised order");
			}
			stopOrder(previousOrder, null);
		} else if (Order.Action.DISCONTINUE.equals(order.getAction())) {
			discontinueExistingOrdersIfNecessary(order);
		}
		
		return saveOrderInternal(order, orderContext);
	}