@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_ADMIN_FUNCTIONS)
	public SortedMap<String, String> getSystemVariables() throws APIException;