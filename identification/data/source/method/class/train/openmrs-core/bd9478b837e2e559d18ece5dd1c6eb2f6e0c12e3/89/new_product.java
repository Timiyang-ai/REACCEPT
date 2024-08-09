@Authorized(OpenmrsConstants.PRIV_DELETE_ORDERS)
	public Order voidOrder(Order order, String voidReason) throws APIException;