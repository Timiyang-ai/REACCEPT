@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_FORMS)
	public Form getForm(String name) throws APIException;