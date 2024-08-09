	@Test
	public void getPatientIdentifierTypeByUuid_shouldFetchPatientIdentifierTypeWithGivenUuid() throws Exception {
		PatientIdentifierType identifierType = Context.getPatientService().getPatientIdentifierTypeByUuid(
		    "1a339fe9-38bc-4ab3-b180-320988c0b968");
		Assert.assertNotNull(identifierType);
		Assert.assertTrue(identifierType.getClass().isAssignableFrom(PatientIdentifierType.class));
	}