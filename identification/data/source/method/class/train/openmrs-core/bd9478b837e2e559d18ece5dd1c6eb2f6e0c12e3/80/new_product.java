@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public List<Encounter> getEncounters(String query, Integer start, Integer length, boolean includeVoided)
	        throws APIException;