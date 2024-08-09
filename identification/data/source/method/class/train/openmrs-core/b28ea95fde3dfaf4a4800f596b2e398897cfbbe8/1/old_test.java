	@Test
	public void getPatients_shouldEscapeAnAsterixCharacterInIdentifierPhrase() {
		//Note that all tests for wildcard should be pass in 2s due to the behaviour of wildcards,
		//that is we test for the size and actual patient object returned
		Patient patient2 = patientService.getPatient(2);
		PatientIdentifier patientIdentifier = new PatientIdentifier("*567", patientService.getPatientIdentifierType(5), Context
		        .getLocationService().getLocation(1));
		patient2.addIdentifier(patientIdentifier);
		patientService.savePatient(patient2);
		
		//add closely matching identifier to a different patient
		Patient patient6 = patientService.getPatient(6);
		PatientIdentifier patientIdentifier6 = new PatientIdentifier("4567", patientService.getPatientIdentifierType(5), Context
		        .getLocationService().getLocation(1));
		patientIdentifier6.setPreferred(true);
		patient6.addIdentifier(patientIdentifier6);
		patientService.savePatient(patient6);

		updateSearchIndex();

		//we expect only one matching patient
		int actualSize = dao.getPatients("*567", 0, null).size();
		Assert.assertEquals(1, actualSize);
		
		//if actually the search returned the matching patient
		Patient actualPatient = dao.getPatients("*567", 0, null).get(0);
		
		Assert.assertEquals(patient2, actualPatient);
	}