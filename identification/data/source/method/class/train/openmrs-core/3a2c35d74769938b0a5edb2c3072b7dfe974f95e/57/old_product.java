@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public List<ConceptSearchResult> getConcepts(String phrase, Locale locale) throws APIException;