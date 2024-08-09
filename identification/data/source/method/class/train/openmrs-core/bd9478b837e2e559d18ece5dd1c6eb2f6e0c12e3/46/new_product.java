@Authorized(PrivilegeConstants.ADD_ORDERS)
	public Order discontinueOrder(Order orderToDiscontinue, String reasonNonCoded, Date discontinueDate) throws Exception;