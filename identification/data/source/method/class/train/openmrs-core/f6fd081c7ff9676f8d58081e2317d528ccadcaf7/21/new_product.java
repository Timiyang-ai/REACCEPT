@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_FORMS)
	public Form getForm(Integer formId) throws APIException;