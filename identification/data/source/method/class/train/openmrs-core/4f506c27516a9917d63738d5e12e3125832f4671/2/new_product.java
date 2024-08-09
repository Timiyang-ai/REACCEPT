@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_DATATYPES)
	public ConceptDatatype getConceptDatatypeByName(String name) throws APIException;