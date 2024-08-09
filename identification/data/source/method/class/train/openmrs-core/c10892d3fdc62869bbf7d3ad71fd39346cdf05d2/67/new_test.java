	@Test
	public void savePatientIdentifier_shouldCreateNewPatientIndentifier() throws Exception {
		PatientIdentifier patientIdentifier = new PatientIdentifier("677-56-6666", new PatientIdentifierType(4),
		        new Location(1));
		Patient associatedPatient = patientService.getPatient(2);
		patientIdentifier.setPatient(associatedPatient);
		PatientIdentifier createdPatientIdentifier = patientService.savePatientIdentifier(patientIdentifier);
		Assert.assertNotNull(createdPatientIdentifier);
		Assert.assertNotNull(createdPatientIdentifier.getPatientIdentifierId());
	}