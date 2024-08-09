public PatientIdentifierType getPatientIdentifierType(String name) throws APIException {
		if (!context.isAuthenticated())
			throw new APIAuthenticationException("Authentication required");
		
		return getPatientDAO().getPatientIdentifierType(name);
	}