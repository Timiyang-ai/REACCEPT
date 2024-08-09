@Authorized( { OpenmrsConstants.PRIV_VIEW_PATIENT_IDENTIFIERS })
	@Transactional(readOnly = true)
	public PatientIdentifier getPatientIdentifier(Integer patientIdentifierId) throws APIException;