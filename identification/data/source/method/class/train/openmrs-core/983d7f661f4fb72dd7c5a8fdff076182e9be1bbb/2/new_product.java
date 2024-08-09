@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_REFERENCE_TERMS)
	public List<ConceptReferenceTerm> getConceptReferenceTerms(boolean includeRetired) throws APIException;