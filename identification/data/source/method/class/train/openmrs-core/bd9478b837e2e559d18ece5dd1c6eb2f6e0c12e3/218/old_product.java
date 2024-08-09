public void discontinueOrder(Order order, String reason) throws APIException {
		getOrderDAO().discontinueOrder(order, reason);
	}