public OrderType getOrderType(Integer orderTypeId) throws APIException {
		return getOrderDAO().getOrderType(orderTypeId);
	}