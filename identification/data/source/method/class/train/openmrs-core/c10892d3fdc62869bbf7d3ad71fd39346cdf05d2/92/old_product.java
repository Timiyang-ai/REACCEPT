@Authorized(OpenmrsConstants.PRIV_VIEW_PATIENTS)
	@Transactional(readOnly = true)
	public boolean isIdentifierInUseByAnotherPatient(PatientIdentifier patientIdentifier);