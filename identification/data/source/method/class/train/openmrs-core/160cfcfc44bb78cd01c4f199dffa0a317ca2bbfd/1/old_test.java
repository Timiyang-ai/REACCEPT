@Test
	@Verifies(value = "should escape an asterix character in identifier phrase", method = "getPatients(String,String,List<QPatientIdentifierType;>,null)")
	public void getPatients_shouldEscapeAnAsterixCharacterInIdentifierPhrase() throws Exception {
		//Note that all tests for wildcard should be pass in 2s due to the behaviour of wildcards,
		//that is we test for the size and actual patient object returned
		Patient patient2 = pService.getPatient(2);
		PatientIdentifier patientIdentifier = new PatientIdentifier("*567", pService.getPatientIdentifierType(2), Context
		        .getLocationService().getLocation(1));
		patient2.addIdentifier(patientIdentifier);
		pService.savePatient(patient2);
		
		//add closely matching identifier to a different patient
		Patient patient6 = pService.getPatient(6);
		PatientIdentifier patientIdentifier6 = new PatientIdentifier("4567", pService.getPatientIdentifierType(2), Context
		        .getLocationService().getLocation(1));
		patient6.addIdentifier(patientIdentifier6);
		pService.savePatient(patient6);
		
		List<PatientIdentifierType> identifierTypes = Collections.emptyList();
		//we expect only one matching patient
		int actualSize = dao.getPatients(null, "*567", identifierTypes, false).size();
		Assert.assertEquals(1, actualSize);
		
		//if actually the search returned the matching patient
		Patient actualPatient = dao.getPatients(null, "*567", identifierTypes, false).get(0);
		
		Assert.assertEquals(patient2, actualPatient);
	}