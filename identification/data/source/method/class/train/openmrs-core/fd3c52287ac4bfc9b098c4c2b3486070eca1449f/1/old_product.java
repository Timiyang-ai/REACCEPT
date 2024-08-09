@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.MANAGE_ENCOUNTER_ROLES })
	public EncounterRole getEncounterRoleByUuid(String uuid) throws APIException;