@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTER_TYPES })
	public EncounterType getEncounterType(String name) throws APIException;