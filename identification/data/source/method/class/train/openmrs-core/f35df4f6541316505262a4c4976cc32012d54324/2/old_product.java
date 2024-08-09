@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public Integer getCountOfEncounters(String query, boolean includeVoided);