@Override
	@Transactional(readOnly = true)
	public List<PatientIdentifierType> getPatientIdentifierTypes(String name, String format, Boolean required,
	        Boolean hasCheckDigit) throws APIException {
		return dao.getPatientIdentifierTypes(name, format, required, hasCheckDigit);
	}