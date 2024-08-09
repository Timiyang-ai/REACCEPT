	@Test
	public void getPatientIdentifierType_shouldFetchPatientIdentifierWithGivenPatientIdentifierTypeId() throws Exception {
		PatientIdentifierType identifierType = Context.getPatientService().getPatientIdentifierType(1);
		Assert.assertNotNull(identifierType);
		Assert.assertTrue(identifierType.getClass().isAssignableFrom(PatientIdentifierType.class));
	}