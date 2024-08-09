@Authorized( { OpenmrsConstants.PRIV_DELETE_PATIENT_IDENTIFIERS })
	public PatientIdentifier voidPatientIdentifier(PatientIdentifier patientIdentifier, String reason) throws APIException;