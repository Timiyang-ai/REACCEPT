@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_IDENTIFIER_TYPES })
	public PatientIdentifierType getPatientIdentifierType(String name)
	        throws APIException;