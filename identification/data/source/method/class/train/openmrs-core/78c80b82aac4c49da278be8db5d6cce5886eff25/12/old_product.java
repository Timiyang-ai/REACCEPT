public EncounterType getEncounterType(Integer encounterTypeId) throws APIException {
		return context.getDAOContext().getEncounterDAO().getEncounterType(encounterTypeId);
	}