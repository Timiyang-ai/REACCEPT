@Transactional(readOnly=true)
	public List<PatientIdentifier> getPatientIdentifiers(String identifier,
			PatientIdentifierType pit) throws APIException;