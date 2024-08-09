public Encounter getEncounter(Integer encounterId) throws APIException {
		return getEncounterDAO().getEncounter(encounterId);
	}