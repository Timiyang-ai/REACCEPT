@Authorized( { OpenmrsConstants.PRIV_MANAGE_IDENTIFIER_TYPES })
	public PatientIdentifierType retirePatientIdentifierType(PatientIdentifierType patientIdentifierType, String reason)
	throws APIException;