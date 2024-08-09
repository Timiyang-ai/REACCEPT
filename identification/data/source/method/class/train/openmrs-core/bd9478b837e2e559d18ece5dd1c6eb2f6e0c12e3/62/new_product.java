@Authorized(OpenmrsConstants.PRIV_EDIT_ORDERS)
	public Order discontinueOrder(Order order, Concept discontinueReason,
			Date discontinueDate) throws APIException;