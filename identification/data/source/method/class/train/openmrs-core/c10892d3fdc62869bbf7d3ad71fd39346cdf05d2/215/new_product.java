@Authorized( { PrivilegeConstants.VIEW_PATIENT_IDENTIFIERS })
	@Transactional(readOnly = true)
	public PatientIdentifier getPatientIdentifier(Integer patientIdentifierId) throws APIException;