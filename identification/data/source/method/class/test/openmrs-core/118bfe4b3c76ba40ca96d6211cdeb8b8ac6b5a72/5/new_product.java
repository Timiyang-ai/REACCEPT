@Authorized( { PrivilegeConstants.GET_PATIENT_IDENTIFIERS })
	public PatientIdentifier getPatientIdentifierByUuid(String uuid) throws APIException;