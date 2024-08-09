@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_MANAGE_IMPLEMENTATION_ID)
	public ImplementationId getImplementationId() throws APIException;