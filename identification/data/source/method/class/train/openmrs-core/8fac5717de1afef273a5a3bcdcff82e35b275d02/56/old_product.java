@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_ADMIN_FUNCTIONS)
	public SortedMap<String, String> getSystemVariables() throws APIException;