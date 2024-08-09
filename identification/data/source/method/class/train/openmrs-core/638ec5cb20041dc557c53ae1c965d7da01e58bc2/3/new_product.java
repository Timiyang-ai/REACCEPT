@Authorized(PrivilegeConstants.EDIT_ORDERS)
	public void discontinueAllOrders(Patient patient, Concept discontinueReason, Date discontinueDate) throws APIException;