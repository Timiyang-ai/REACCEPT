@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsByClass(ConceptClass cc) throws APIException;