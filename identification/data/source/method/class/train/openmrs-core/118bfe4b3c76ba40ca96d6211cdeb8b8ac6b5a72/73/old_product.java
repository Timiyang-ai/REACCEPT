@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_DATATYPES)
	public ConceptDatatype getConceptDatatypeByUuid(String uuid);