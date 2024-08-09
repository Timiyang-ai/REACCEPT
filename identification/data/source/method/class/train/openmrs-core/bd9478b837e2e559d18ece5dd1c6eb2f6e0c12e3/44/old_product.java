public List<PatientIdentifierType> getPatientIdentifierTypes() throws APIException {
		if (!context.isAuthenticated())
			throw new APIAuthenticationException("Authentication required");
		
		return getPatientDAO().getPatientIdentifierTypes();
	}