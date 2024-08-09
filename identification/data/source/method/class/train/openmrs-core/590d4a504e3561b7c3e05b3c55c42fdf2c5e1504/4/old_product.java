@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.MANAGE_IMPLEMENTATION_ID)
	public ImplementationId getImplementationId() throws APIException;