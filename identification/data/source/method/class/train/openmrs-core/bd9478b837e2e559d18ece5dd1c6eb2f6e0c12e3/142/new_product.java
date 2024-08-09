@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_ORDER_TYPES)
	public List<OrderType> getOrderTypes() throws APIException;