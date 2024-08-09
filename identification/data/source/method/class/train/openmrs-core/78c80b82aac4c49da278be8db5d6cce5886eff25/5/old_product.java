@Transactional(readOnly=true)
	public EncounterType getEncounterType(String name) throws APIException;