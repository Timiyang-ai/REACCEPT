@Test(expected = DuplicateIdentifierException.class)
	public void checkPatientIdentifiers_shouldThrowErrorWhenPatientHasIdenticalIdentifiers() throws Exception {
		
		PatientIdentifierType patientIdentifierType = Context.getPatientService().getAllPatientIdentifierTypes(false).get(0);
		
		Patient patient = new Patient();
		// Identifier #1
		
		PatientIdentifier patientIdentifier1 = new PatientIdentifier();
		patientIdentifier1.setIdentifier("123456789");
		patientIdentifier1.setLocation( new Location(2) );
		patientIdentifier1.setIdentifierType(patientIdentifierType);
		patient.addIdentifier(patientIdentifier1);
		
		// Identifier #2
		PatientIdentifier patientIdentifier2 = new PatientIdentifier();
		patientIdentifier2.setIdentifier("123456789");
		patientIdentifier2.setIdentifierType(patientIdentifierType);
		patientIdentifier2.setLocation( new Location(2) );
		patient.addIdentifier(patientIdentifier2);
		patientService.checkPatientIdentifiers(patient);
		
	}