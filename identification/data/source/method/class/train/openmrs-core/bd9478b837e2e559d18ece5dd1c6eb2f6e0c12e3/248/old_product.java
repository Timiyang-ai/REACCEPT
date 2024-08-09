public void voidOrder(Order order, String reason) throws APIException {
		getOrderDAO().voidOrder(order, reason);
	}