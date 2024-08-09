@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_IDENTIFIER_TYPES })
	public List<PatientIdentifierType> getAllPatientIdentifierTypes() throws APIException;