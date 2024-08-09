	@Test
	public void checkPatientIdentifiers_shouldThrowMissingRequiredIdentifierGivenRequiredIdentifierTypeMissing()
		throws Exception {
		// given
		final PatientIdentifierType requiredIdentifierType = new PatientIdentifierType(12345);
		requiredIdentifierType.setUuid("some type uuid");
		requiredIdentifierType.setName("NameOfRequiredIdentifierType");
		final PatientIdentifierType patientIdentifierType = new PatientIdentifierType(6789);
		patientIdentifierType.setUuid("another type uuid");
		patientIdentifierType.setName("NameOfPatientIdentifierType");

		final List<PatientIdentifierType> requiredTypes = new ArrayList<>();
		requiredTypes.add(requiredIdentifierType);
		when(patientDaoMock.getPatientIdentifierTypes(any(), any(), any(), any()))
			.thenReturn(requiredTypes);

		final Patient patientWithIdentifiers = new Patient();
		patientWithIdentifiers
			.addIdentifier(new PatientIdentifier("some identifier", patientIdentifierType, mock(Location.class)));

		try {
			// when
			patientService.checkPatientIdentifiers(patientWithIdentifiers);
			fail();
			// then
		}
		catch (MissingRequiredIdentifierException e) {
			assertTrue(e.getMessage().contains("required"));
			assertTrue(e.getMessage().contains("NameOfRequiredIdentifierType"));
		}
		catch (Exception e) {
			fail("Expecting MissingRequiredIdentifierException");
		}

	}