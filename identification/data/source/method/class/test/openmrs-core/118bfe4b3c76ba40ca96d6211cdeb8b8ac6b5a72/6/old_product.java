@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_IDENTIFIER_TYPES })
	public PatientIdentifierType getPatientIdentifierTypeByUuid(String uuid) throws APIException;