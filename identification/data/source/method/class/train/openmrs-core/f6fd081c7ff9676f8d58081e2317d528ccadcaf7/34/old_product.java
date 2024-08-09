@Deprecated
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_FORMS)
	public List<Form> getForms(boolean publishedOnly, boolean includeRetired) throws APIException;