@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PATIENT_IDENTIFIERS })
	public PatientIdentifier getPatientIdentifierByUuid(String uuid) throws APIException;