@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_IDENTIFIER_TYPES })
	public List<PatientIdentifierType> getPatientIdentifierTypes() throws APIException;