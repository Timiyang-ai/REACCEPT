@Transactional(readOnly = true)
	public PatientIdentifier getPatientIdentifierByUuid(String uuid) throws APIException;