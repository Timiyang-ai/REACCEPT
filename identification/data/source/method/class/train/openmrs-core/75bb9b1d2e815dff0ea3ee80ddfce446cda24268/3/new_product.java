@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PATIENT_IDENTIFIERS })
	public void checkPatientIdentifiers(Patient patient) throws PatientIdentifierException;