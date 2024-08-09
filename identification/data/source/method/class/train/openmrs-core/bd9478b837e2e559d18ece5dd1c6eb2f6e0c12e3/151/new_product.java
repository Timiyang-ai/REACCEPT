@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_ORDER_TYPES)
	public List<OrderType> getOrderTypes() throws APIException;