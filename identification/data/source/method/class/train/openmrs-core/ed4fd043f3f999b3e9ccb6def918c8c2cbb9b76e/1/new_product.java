@Authorized(PrivilegeConstants.ADD_ORDERS)
	public Order discontinueOrder(Order orderToDiscontinue, String reasonNonCoded, Date discontinueDate, Provider orderer,
	        Encounter encounter) throws Exception;