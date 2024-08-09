@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTER_TYPES })
	public List<EncounterType> findEncounterTypes(String name) throws APIException;