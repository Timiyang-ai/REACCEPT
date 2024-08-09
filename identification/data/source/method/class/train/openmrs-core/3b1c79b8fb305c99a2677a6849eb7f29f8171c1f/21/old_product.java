@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_MAP_TYPES)
	public List<ConceptMapType> getActiveConceptMapTypes() throws APIException;