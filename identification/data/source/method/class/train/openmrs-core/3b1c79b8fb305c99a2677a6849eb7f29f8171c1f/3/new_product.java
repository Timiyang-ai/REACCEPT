@Authorized(PrivilegeConstants.GET_CONCEPT_MAP_TYPES)
	public List<ConceptMapType> getConceptMapTypes(boolean includeRetired, boolean includeHidden) throws APIException;