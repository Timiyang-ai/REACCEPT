@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public Encounter getEncounterByUuid(String uuid) throws APIException;