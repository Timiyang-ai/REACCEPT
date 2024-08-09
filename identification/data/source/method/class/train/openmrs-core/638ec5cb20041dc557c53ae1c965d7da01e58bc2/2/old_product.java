@Authorized(PrivilegeConstants.VIEW_ORDERS)
	public List<Order> getOrderHistoryByOrderNumber(String orderNumber);