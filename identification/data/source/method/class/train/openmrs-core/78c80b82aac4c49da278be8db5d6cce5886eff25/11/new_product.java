@Transactional(readOnly=true)
	public EncounterType getEncounterType(Integer encounterTypeId) throws APIException;