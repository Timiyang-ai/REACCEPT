@Transactional(readOnly = true)
	public PatientIdentifierType getPatientIdentifierTypeByUuid(String uuid) throws APIException;