@Authorized(PrivilegeConstants.GET_ORDERS)
	public List<Order> getActiveOrders(Patient patient, OrderType orderType, CareSetting careSetting, Date asOfDate);