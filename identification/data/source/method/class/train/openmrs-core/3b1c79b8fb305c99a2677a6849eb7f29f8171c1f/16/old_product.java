@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_REFERENCE_TERMS)
	public ConceptReferenceTerm getConceptReferenceTermByUuid(String uuid) throws APIException;