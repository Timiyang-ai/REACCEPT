@Deprecated
	@Transactional(readOnly = true)
	public List<PatientIdentifierType> getPatientIdentifierTypes() throws APIException {
		return Context.getPatientService().getAllPatientIdentifierTypes();
	}