@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_ENCOUNTER_TYPES })
	public EncounterType getEncounterTypeByUuid(String uuid) throws APIException;