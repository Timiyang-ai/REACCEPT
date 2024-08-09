@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_IDENTIFIER_TYPES })
	public PatientIdentifierType getPatientIdentifierTypeByName(String name) throws APIException;