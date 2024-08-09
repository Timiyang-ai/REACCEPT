public List<Order> getOrders() throws APIException {
		return getOrders(Order.class, null, null, null, null, null, null);
	}