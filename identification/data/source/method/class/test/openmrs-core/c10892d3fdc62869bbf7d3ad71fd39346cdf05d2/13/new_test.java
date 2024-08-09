	@Test
	public void getPatientIdentifierTypeByName_shouldFetchPatientIdentifierTypeThatExactlyMatchesGivenName()
	    throws Exception {
		
		String identifierTypeName = "OpenMRS Identification Number";
		PatientIdentifierType identifierType = Context.getPatientService()
		        .getPatientIdentifierTypeByName(identifierTypeName);
		Assert.assertNotNull(identifierType);
		Assert.assertEquals(identifierType.getName(), identifierTypeName);
		Assert.assertTrue(identifierType.getClass().isAssignableFrom(PatientIdentifierType.class));
	}