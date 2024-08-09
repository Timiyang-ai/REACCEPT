@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_ORDERS)
	public List<Order> getOrders() throws APIException;