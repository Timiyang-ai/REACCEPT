	@Test
	public void purgePatientIdentifier_shouldDeletePatientIdentifierFromDatabase() throws Exception {
		PatientIdentifier patientIdentifier = patientService.getPatientIdentifier(7);
		patientService.purgePatientIdentifier(patientIdentifier);
		Assert.assertNull(patientService.getPatientIdentifier(7));
		
	}