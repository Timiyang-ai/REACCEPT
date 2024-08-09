@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public List<Concept> getConceptsByName(String name, Locale locale) throws APIException;