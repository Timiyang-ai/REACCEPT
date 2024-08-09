public OrderType getOrderType(Integer orderTypeId) throws APIException {
		return context.getDAOContext().getOrderDAO().getOrderType(orderTypeId);
	}