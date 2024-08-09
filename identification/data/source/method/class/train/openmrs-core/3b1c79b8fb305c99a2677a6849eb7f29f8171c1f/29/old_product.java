@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_REFERENCE_TERMS)
	public ConceptReferenceTerm getConceptReferenceTermByCode(String code, ConceptSource conceptSource) throws APIException;