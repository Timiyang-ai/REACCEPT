@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public EncounterRole getEncounterRoleByUuid(String uuid) throws APIException;