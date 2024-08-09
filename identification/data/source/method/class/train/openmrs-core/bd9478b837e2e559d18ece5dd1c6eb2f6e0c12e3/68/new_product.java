@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public List<Encounter> getEncounters(String query, Integer start, Integer length, boolean includeVoided)
	        throws APIException;