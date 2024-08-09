@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_FORMS)
	public List<Form> getAllForms() throws APIException;