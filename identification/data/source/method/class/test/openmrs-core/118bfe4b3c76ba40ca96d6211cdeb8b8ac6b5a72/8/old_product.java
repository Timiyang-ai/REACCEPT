@Transactional(readOnly = true)
	public Encounter getEncounterByUuid(String uuid) throws APIException;