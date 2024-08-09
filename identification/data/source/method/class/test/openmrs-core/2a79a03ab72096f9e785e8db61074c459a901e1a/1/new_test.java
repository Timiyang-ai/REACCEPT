	@Test
	public void getAllIdentifierValidators_shouldReturnAllRegisteredIdentifierValidators() throws Exception {
		Collection<IdentifierValidator> expectedValidators = new HashSet<>();
		expectedValidators.add(patientService.getIdentifierValidator("org.openmrs.patient.impl.LuhnIdentifierValidator"));
		expectedValidators
		        .add(patientService.getIdentifierValidator("org.openmrs.patient.impl.VerhoeffIdentifierValidator"));
		Assert.assertEquals(2, patientService.getAllIdentifierValidators().size());
		assertCollectionContentsEquals(expectedValidators, patientService.getAllIdentifierValidators());
	}