	@Test
	public void getPatientIdentifierByUuid_shouldFindObjectGivenValidUuid() throws Exception {
		String uuid = "ff41928c-3bca-48d9-a4dc-9198f6b2873b";
		PatientIdentifier patientIdentifier = Context.getPatientService().getPatientIdentifierByUuid(uuid);
		Assert.assertEquals(1, (int) patientIdentifier.getPatientIdentifierId());
	}