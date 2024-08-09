@Test
	@Verifies(value = "should escape percentage character in name phrase", method = "getPatients(String,String,List<QPatientIdentifierType;>,null)")
	public void getPatients_shouldEscapePercentageCharacterInNamePhrase() throws Exception {
		
		Patient patient2 = pService.getPatient(2);
		PersonName name = new PersonName("%cats", "and", "dogs");
		patient2.addName(name);
		pService.savePatient(patient2);
		
		//add a new closely matching identifier to another patient
		Patient patient6 = pService.getPatient(6);
		PersonName name6 = new PersonName("acats", "and", "dogs");
		patient6.addName(name6);
		patient6.getPatientIdentifier().setPreferred(true);
		pService.savePatient(patient6);
		
		List<PatientIdentifierType> identifierTypes = Collections.emptyList();
		//we expect only one matching patient
		int actualSize = dao.getPatients("%ca", null, identifierTypes, false, 0, null, false).size();
		Assert.assertEquals(1, actualSize);
		
		Patient actualPatient = dao.getPatients("%ca", null, identifierTypes, false, 0, null, false).get(0);
		//if actually the search returned the matching patient
		Assert.assertEquals(patient2, actualPatient);
	}