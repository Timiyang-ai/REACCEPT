public EncounterType getEncounterType(Integer encounterTypeId) throws APIException {
		if (!context.isAuthenticated())
			throw new APIAuthenticationException("Authentication required");
		
		return getEncounterDAO().getEncounterType(encounterTypeId);
	}