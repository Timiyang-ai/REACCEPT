@Authorized(PrivilegeConstants.VIEW_ORDERS)
	public List<Order> getOrders(Patient patient, CareSetting careSetting, OrderType orderType, boolean includeVoided);