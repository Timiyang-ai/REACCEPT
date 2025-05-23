@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_ENCOUNTER_TYPES })
	public List<EncounterType> getAllEncounterTypes() throws APIException;