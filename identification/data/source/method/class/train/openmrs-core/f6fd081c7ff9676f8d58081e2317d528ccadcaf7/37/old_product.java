@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_FORMS)
	public Form getForm(String name) throws APIException;