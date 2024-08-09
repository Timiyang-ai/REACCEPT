@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_GLOBAL_PROPERTIES)
	public List<GlobalProperty> getAllGlobalProperties() throws APIException;