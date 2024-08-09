@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_IDENTIFIER_TYPES })
	public PatientIdentifierType getPatientIdentifierType(String name) throws APIException;