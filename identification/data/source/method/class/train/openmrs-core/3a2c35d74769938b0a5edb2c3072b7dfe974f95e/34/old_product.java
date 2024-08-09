@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Concept getConcept(Integer conceptId) throws APIException;