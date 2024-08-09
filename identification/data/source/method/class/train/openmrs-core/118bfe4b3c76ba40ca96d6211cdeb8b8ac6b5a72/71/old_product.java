@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_SOURCES)
	public ConceptSource getConceptSourceByName(String conceptSourceName) throws APIException;