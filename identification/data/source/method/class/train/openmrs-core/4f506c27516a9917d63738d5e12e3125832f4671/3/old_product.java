@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_DATATYPES)
	public ConceptDatatype getConceptDatatypeByName(String name) throws APIException;