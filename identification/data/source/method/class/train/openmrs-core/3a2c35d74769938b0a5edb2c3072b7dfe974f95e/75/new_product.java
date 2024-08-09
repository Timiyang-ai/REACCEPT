@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PATIENT_IDENTIFIERS })
	public List<PatientIdentifier> getPatientIdentifiers(
	        PatientIdentifierType patientIdentifierType) throws APIException;