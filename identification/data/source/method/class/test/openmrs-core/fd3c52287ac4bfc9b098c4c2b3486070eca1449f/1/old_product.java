@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTER_ROLES })
	public EncounterRole getEncounterRoleByUuid(String uuid) throws APIException;