public EncounterType getEncounterType(String name) throws APIException {
		if (!context.isAuthenticated())
			throw new APIAuthenticationException("Authentication required");
		
		return getEncounterDAO().getEncounterType(name);
	}