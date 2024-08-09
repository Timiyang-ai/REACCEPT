@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTER_ROLES })
	public List<EncounterRole> getAllEncounterRoles(boolean includeRetired);