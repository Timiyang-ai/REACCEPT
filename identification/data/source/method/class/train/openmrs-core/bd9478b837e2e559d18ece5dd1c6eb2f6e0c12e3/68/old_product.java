@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public List<EncounterSearchResult> getEncounters(String query, Integer start, Integer length, boolean includeVoided,
	        boolean sortbyNames) throws APIException;