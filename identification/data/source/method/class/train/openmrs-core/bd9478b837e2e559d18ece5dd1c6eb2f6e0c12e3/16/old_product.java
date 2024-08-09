@Transactional(readOnly=true)
	public Encounter getEncounter(Integer encounterId) throws APIException;