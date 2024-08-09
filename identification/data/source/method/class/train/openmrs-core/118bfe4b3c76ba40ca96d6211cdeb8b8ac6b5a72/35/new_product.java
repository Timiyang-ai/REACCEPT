@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_CLASSES)
	public ConceptClass getConceptClassByUuid(String uuid);