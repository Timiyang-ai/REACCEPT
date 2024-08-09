@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Concept getConceptByName(String name) throws APIException;