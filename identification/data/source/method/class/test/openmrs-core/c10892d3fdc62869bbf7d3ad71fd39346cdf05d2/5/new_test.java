	@Test
	public void savePatient_shouldCreateNewPatientFromExistingPersonPlusUserObject() throws Exception {
		// sanity check, make sure there isn't a 501 patient already
		Patient oldPatient = patientService.getPatient(501);
		Assert.assertNull(oldPatient);
		
		// fetch Bruno from the database
		Person existingPerson = Context.getPersonService().getPerson(501);
		Context.clearSession();
		Patient patient = new Patient(existingPerson);
		PatientIdentifier patientIdentifier = new PatientIdentifier("some identifier", new PatientIdentifierType(2),
		        new Location(1));
		patientIdentifier.setPreferred(true);
		patient.addIdentifier(patientIdentifier);
		
		patientService.savePatient(patient);
		
		Assert.assertEquals(501, patient.getPatientId().intValue());
		// make sure a new row with a patient id WAS created
		Assert.assertNotNull(patientService.getPatient(501));
		// make sure a new row with a new person id WASN'T created
		Assert.assertNull(patientService.getPatient(503));
	}