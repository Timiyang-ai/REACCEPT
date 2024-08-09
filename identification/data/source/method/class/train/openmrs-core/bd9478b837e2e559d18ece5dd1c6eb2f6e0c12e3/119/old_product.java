public void discontinueOrder(Order order, String reason) throws APIException {
		context.getDAOContext().getOrderDAO().discontinueOrder(order, reason);
	}