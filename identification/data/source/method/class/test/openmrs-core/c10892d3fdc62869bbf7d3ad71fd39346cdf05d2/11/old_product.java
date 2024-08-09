@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_GLOBAL_PROPERTIES)
	public List<GlobalProperty> getAllGlobalProperties() throws APIException;