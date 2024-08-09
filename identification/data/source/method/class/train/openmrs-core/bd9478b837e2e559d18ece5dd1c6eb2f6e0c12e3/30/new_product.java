@Authorized( { PrivilegeConstants.ADD_ORDERS, PrivilegeConstants.EDIT_ORDERS })
	public Order discontinueOrder(Order orderToDiscontinue, Concept reasonCoded, Date discontinueDate, Provider orderer,
	        Encounter encounter) throws Exception;