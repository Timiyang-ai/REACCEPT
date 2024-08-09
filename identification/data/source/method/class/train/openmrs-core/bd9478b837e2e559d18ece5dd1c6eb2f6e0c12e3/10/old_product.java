public Encounter getEncounter(Integer encounterId) throws APIException {
		return context.getDAOContext().getEncounterDAO().getEncounter(encounterId);
	}