@Authorized(PrivilegeConstants.EDIT_ORDERS)
	public Order discontinueOrder(Order order, String reason, User user, Date discontinueDate) throws APIException;