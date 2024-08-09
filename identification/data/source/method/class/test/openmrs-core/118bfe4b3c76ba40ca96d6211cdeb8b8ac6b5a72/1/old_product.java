@Transactional(readOnly = true)
	public EncounterType getEncounterTypeByUuid(String uuid) throws APIException;