@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public Encounter getEncounterByUuid(String uuid) throws APIException;