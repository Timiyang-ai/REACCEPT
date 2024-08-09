@Override
	@Transactional(readOnly = true)
	public List<PatientIdentifierType> getPatientIdentifierTypes(String name, String format, Boolean required,
	        Boolean hasCheckDigit) throws APIException {
		List<PatientIdentifierType> patientIdentifierTypes = dao.getPatientIdentifierTypes(name, format, required, hasCheckDigit);
		if (patientIdentifierTypes == null) {
			return new ArrayList<>();
		}
		return patientIdentifierTypes;
	}