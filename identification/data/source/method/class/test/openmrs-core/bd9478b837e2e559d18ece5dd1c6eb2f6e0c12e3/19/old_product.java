@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_ORDER_TYPES)
	public OrderType getOrderType(Integer orderTypeId) throws APIException;