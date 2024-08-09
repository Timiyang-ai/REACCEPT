@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTER_TYPES })
	public EncounterType getEncounterType(String name) throws APIException;