@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_ENCOUNTER_TYPES })
	public EncounterType getEncounterType(String name) throws APIException;