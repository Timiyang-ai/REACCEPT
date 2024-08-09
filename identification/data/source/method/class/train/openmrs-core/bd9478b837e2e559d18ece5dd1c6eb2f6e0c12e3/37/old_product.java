@Transactional(readOnly=true)
	public OrderType getOrderType(Integer orderTypeId) throws APIException;