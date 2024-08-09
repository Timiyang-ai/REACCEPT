@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public List<Concept> getConceptsByClass(ConceptClass cc) throws APIException;