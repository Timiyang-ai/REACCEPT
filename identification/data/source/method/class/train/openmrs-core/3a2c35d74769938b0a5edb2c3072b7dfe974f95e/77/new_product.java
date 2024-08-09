@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public List<Concept> getConceptsByName(String name, Locale locale, Boolean exactLocale) throws APIException;