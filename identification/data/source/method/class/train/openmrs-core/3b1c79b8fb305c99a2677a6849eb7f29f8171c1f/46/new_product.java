@Deprecated
	@Transactional(readOnly = true)
	public List<PatientIdentifierType> getPatientIdentifierTypes() throws APIException {
		return getAllPatientIdentifierTypes();
	}