@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Concept getConcept(String conceptIdOrName) throws APIException;