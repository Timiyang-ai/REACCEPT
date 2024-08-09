@Ignore
	@Test
	@Verifies(value = "should logged in user should load their own patient object", method = "findPatients(String,null)")
	public void findPatients_shouldLoggedInUserShouldLoadTheirOwnPatientObject() throws Exception {
		executeDataSet("org/openmrs/web/dwr/include/DWRPatientService-patientisauser.xml");
		DWRPatientService dwrService = new DWRPatientService();
		Collection<Object> resultObjects = dwrService.findPatients("Super", false, null, null);
		Assert.assertEquals(1, resultObjects.size());
	}