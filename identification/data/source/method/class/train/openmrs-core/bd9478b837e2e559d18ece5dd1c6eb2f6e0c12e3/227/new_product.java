@Authorized(PrivilegeConstants.ADD_ORDERS)
	public Order discontinueOrder(Order orderToDiscontinue, Concept reasonCoded, Date discontinueDate) throws Exception;