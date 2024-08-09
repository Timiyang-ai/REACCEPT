	@Test
	public void getPatientIdentifier_shouldReturnThePatientsIdentifier() throws Exception {
		
		Assert.assertEquals("101-6", patientService.getPatientIdentifier(2).getIdentifier());
		Assert.assertEquals(1, patientService.getPatientIdentifier(2).getIdentifierType().getPatientIdentifierTypeId()
		        .intValue());
	}