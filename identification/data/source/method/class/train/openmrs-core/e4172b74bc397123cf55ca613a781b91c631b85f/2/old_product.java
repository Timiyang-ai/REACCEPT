@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.GET_ORDERS)
	public Order getOrderByUuid(String uuid) throws APIException;