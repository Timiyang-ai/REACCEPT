@Authorized( { OpenmrsConstants.PRIV_EDIT_ORDERS, OpenmrsConstants.PRIV_ADD_ORDERS })
	public Order saveOrder(Order order) throws APIException;