@Authorized(PrivilegeConstants.GET_ORDERS)
	public List<Order> getOrderHistoryByOrderNumber(String orderNumber);