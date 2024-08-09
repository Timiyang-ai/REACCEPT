@Deprecated
	@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_FORMS)
	public List<Form> getForms(boolean publishedOnly) throws APIException;