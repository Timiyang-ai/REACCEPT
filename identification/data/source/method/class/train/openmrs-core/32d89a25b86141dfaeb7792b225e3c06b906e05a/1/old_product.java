@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.MANAGE_ENCOUNTER_ROLES })
	public List<EncounterRole> getAllEncounterRoles(boolean includeRetired);