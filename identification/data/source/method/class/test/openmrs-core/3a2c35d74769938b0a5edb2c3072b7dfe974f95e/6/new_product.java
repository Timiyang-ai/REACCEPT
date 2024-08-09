@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public List<Drug> getDrugs(String searchPhrase, Locale locale, boolean exactLocale, boolean includeRetired);