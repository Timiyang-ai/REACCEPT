	@Test
	public void getPatientIdentifiers_shouldReturnOnlyNonVoidedPatientsAndPatientIdentifiers() throws Exception {
		// sanity check. make sure there is at least one voided patient
		Patient patient = patientService.getPatient(999);
		Assert.assertTrue("This patient should be voided", patient.getVoided());
		
		// now fetch all identifiers
		List<PatientIdentifier> patientIdentifiers = patientService.getPatientIdentifiers(null, null, null, null, null);
		for (PatientIdentifier patientIdentifier : patientIdentifiers) {
			Assert.assertFalse("No voided identifiers should be returned", patientIdentifier.getVoided());
			Assert.assertFalse("No identifiers of voided patients should be returned", patientIdentifier.getPatient()
			        .getVoided());
		}
	}