@Deprecated
	@Authorized( { PrivilegeConstants.VIEW_PATIENT_IDENTIFIERS })
	public List<PatientIdentifier> getPatientIdentifiers(PatientIdentifierType patientIdentifierType) throws APIException;