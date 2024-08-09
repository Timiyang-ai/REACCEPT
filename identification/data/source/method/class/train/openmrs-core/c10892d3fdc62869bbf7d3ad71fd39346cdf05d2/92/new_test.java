	@Test
	public void isIdentifierInUseByAnotherPatient_shouldIgnoreVoidedPatientIdentifiers() throws Exception {
		PatientIdentifierType pit = patientService.getPatientIdentifierType(2);
		PatientIdentifier patientIdentifier = new PatientIdentifier("ABC123", pit, null);
		Assert.assertFalse(patientService.isIdentifierInUseByAnotherPatient(patientIdentifier));
	}