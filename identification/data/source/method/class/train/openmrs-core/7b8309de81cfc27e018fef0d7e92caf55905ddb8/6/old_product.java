@Authorized(PrivilegeConstants.GET_ORDERS)
	public List<Order> getOrdersByPatient(Patient patient) throws APIException;