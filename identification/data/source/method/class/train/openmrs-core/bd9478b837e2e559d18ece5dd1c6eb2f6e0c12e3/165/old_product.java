public void unvoidOrder(Order order) throws APIException {
		context.getDAOContext().getOrderDAO().unvoidOrder(order);
	}