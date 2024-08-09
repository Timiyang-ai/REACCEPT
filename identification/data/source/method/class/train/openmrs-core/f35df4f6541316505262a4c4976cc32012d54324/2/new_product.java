@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public Integer getCountOfEncounters(String query, boolean includeVoided);