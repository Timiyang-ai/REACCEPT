@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTER_TYPES })
	public List<EncounterType> getAllEncounterTypes() throws APIException;