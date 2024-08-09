@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_MAP_TYPES)
	public ConceptMapType getConceptMapTypeByName(String name) throws APIException;