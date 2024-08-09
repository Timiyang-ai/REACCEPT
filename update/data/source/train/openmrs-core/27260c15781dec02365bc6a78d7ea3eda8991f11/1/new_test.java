@Test
	@Verifies(value = "should escape underscore character in identifier phrase", method = "getPatients(String,String,List<QPatientIdentifierType;>,null)")
	public void getPatients_shouldEscapeUnderscoreCharacterInIdentifierPhrase() throws Exception {
		baseSetupWithStandardDataAndAuthentication();
		Patient patient2 = pService.getPatient(2);
		PatientIdentifier patientIdentifier = new PatientIdentifier("_567", pService.getPatientIdentifierType(5), Context
		        .getLocationService().getLocation(1));
		patient2.addIdentifier(patientIdentifier);
		pService.savePatient(patient2);
		
		//add closely matching identifier to a different patient
		Patient patient6 = pService.getPatient(6);
		PatientIdentifier patientIdentifier6 = new PatientIdentifier("4567", pService.getPatientIdentifierType(5), Context
		        .getLocationService().getLocation(1));
		patientIdentifier6.setPreferred(true);
		patient6.addIdentifier(patientIdentifier6);
		pService.savePatient(patient6);
		
		List<PatientIdentifierType> identifierTypes = Collections.emptyList();
		//we expect only one matching patient
		int actualSize = dao.getPatients(null, "_567", identifierTypes, false, 0, null, false).size();
		Assert.assertEquals(1, actualSize);
		
		//if actually the search returned the matching patient
		Patient actualPatient = dao.getPatients(null, "_567", identifierTypes, false, 0, null, false).get(0);
		
		Assert.assertEquals(patient2, actualPatient);
	}