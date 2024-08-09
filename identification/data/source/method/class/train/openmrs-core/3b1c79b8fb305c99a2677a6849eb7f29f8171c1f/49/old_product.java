@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_MAP_TYPES)
	public List<ConceptMapType> getConceptMapTypes(boolean includeRetired, boolean includeHidden) throws APIException;