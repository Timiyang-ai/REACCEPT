	@Test
	public void getPatientIdentifierTypes_shouldReturnPatientIdentifierTypesFromDao() {

		// given
		final List<PatientIdentifierType> expectedIdentifierTypes = new ArrayList<>();
		expectedIdentifierTypes.add(new PatientIdentifierType(12345));
		when(patientDaoMock.getPatientIdentifierTypes(any(), any(), any(), any()))
			.thenReturn(expectedIdentifierTypes);

		// when
		final List<PatientIdentifierType> actualIdentifierTypes = patientService
			.getPatientIdentifierTypes("a name", "a format", true, false);

		// then
		verify(patientDaoMock, times(1)).getPatientIdentifierTypes("a name", "a format", true, false);
		assertEquals(expectedIdentifierTypes.get(0).getPatientIdentifierTypeId(),
			actualIdentifierTypes.get(0).getPatientIdentifierTypeId());
	}