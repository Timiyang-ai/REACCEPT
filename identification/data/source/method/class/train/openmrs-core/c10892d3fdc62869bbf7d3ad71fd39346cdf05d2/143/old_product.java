@Authorized(PrivilegeConstants.VIEW_PATIENTS)
	@Transactional(readOnly = true)
	public boolean isIdentifierInUseByAnotherPatient(PatientIdentifier patientIdentifier);