public EncounterType getEncounterType(Integer encounterTypeId) throws APIException {
		return getEncounterDAO().getEncounterType(encounterTypeId);
	}