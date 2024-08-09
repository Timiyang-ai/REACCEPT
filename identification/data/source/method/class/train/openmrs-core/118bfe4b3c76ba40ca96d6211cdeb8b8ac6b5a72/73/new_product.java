@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_DATATYPES)
	public ConceptDatatype getConceptDatatypeByUuid(String uuid);