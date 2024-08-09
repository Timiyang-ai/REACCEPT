@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_REFERENCE_TERMS)
	public ConceptReferenceTerm getConceptReferenceTermByName(String name, ConceptSource conceptSource) throws APIException;