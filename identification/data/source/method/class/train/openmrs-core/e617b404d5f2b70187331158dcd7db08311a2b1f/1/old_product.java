@Transactional(readOnly = true)
	public OrderType getOrderTypeByUuid(String uuid) throws APIException;