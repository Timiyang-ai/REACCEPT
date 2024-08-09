@Transactional(readOnly=true)
	public PatientIdentifierType getPatientIdentifierType(String name)
			throws APIException;