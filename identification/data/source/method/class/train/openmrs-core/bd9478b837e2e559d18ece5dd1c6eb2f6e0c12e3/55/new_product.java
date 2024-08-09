@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_IDENTIFIER_TYPES })
	public List<PatientIdentifierType> getPatientIdentifierTypes(String name, String format, Boolean required,
		Boolean hasCheckDigit) throws APIException;