@Transactional(readOnly=true)
	public List<PatientIdentifier> getPatientIdentifiers(
			PatientIdentifierType pit) throws APIException;