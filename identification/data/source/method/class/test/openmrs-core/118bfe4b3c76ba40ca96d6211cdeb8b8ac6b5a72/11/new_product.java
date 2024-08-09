@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTER_TYPES })
	public EncounterType getEncounterTypeByUuid(String uuid) throws APIException;