@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_FORMS)
	public List<Form> getForms() throws APIException;