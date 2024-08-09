@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTER_TYPES })
	public EncounterType getEncounterTypeByUuid(String uuid) throws APIException;