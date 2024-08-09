public void voidOrder(Order order, String reason) throws APIException {
		context.getDAOContext().getOrderDAO().voidOrder(order, reason);
	}