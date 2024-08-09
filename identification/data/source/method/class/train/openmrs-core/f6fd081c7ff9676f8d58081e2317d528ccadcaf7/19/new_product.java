@Deprecated
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_FORMS)
	public List<Form> getForms() throws APIException;