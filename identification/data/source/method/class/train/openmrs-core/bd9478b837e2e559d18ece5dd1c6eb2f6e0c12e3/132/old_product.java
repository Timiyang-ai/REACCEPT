@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_IDENTIFIER_TYPES })
	public PatientIdentifierType getPatientIdentifierType(Integer patientIdentifierTypeId) throws APIException;