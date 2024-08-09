@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTER_TYPES })
	public List<EncounterType> getAllEncounterTypes() throws APIException;