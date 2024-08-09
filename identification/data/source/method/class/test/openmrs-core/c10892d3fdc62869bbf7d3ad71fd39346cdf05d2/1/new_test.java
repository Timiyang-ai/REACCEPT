	@Test
	public void voidPatientIdentifier_shouldVoidGivenPatientIdentifierWithGivenReason() throws Exception {
		Patient patient = patientService.getPatientIdentifier(3).getPatient();
		int oldActiveIdentifierSize = patient.getActiveIdentifiers().size();
		PatientIdentifier patientIdentifierToVoid = patientService.getPatientIdentifier(3);
		
		PatientIdentifier voidedIdentifier = patientService.voidPatientIdentifier(patientIdentifierToVoid, "Testing");
		// was the void reason set
		Assert.assertEquals("Testing", voidedIdentifier.getVoidReason());
		// patient's active identifiers must have reduced by 1 if the identifier
		// was successfully voided
		Assert.assertEquals(oldActiveIdentifierSize - 1, patient.getActiveIdentifiers().size());
	}