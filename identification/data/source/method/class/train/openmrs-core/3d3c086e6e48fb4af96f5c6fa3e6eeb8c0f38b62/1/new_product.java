public Order saveOrder(Order order) throws APIException {
		if (order.getOrderId() == null && !StringUtils.hasText(order.getOrderNumber())) {
			//TODO call module registered order number generators 
			//and if there is none, use the default below
			order.setOrderNumber(getNewOrderNumber());
		}
		
		return dao.saveOrder(order);
	}