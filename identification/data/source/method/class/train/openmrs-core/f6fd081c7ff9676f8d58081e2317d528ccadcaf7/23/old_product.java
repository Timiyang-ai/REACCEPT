@Deprecated
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_FORMS)
	public List<Form> getForms(boolean publishedOnly) throws APIException;