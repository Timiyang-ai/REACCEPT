@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_REFERENCE_TERMS)
	public List<ConceptReferenceTermMap> getReferenceTermMappingsTo(ConceptReferenceTerm term) throws APIException;