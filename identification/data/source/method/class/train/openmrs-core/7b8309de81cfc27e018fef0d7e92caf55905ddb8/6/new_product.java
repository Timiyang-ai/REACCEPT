@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_ORDERS)
	public List<Order> getOrdersByPatient(Patient patient) throws APIException;