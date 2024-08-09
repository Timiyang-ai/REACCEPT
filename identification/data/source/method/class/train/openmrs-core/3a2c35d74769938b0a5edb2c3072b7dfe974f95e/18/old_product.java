public List<PatientIdentifier> getPatientIdentifiers(String identifier, PatientIdentifierType pit) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_VIEW_PATIENTS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_VIEW_PATIENTS);
		
		return getPatientDAO().getPatientIdentifiers(identifier, pit);
	}