public void unvoidOrder(Order order) throws APIException {
		getOrderDAO().unvoidOrder(order);
	}