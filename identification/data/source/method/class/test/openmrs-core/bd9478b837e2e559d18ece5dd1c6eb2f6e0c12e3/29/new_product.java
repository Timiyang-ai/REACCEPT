public PatientIdentifierType getPatientIdentifierType(Integer patientIdentifierTypeId) throws APIException {
		if (!context.isAuthenticated())
			throw new APIAuthenticationException("Authentication required");
		
		return getPatientDAO().getPatientIdentifierType(patientIdentifierTypeId);
	}