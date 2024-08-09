@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PATIENT_IDENTIFIERS })
	public List<PatientIdentifier> getPatientIdentifiers(String identifier,
		List<PatientIdentifierType> patientIdentifierTypes,
		List<Location> locations, List<Patient> patients,
		Boolean isPreferred) throws APIException;