@Test
	@Verifies(value = "should not throw a NonUniqueObjectException when called with a hand constructed patient", method = "savePatient(Patient)")
	public void savePatient_shouldNotThrowANonUniqueObjectExceptionWhenCalledWithAHandConstructedPatient() throws Exception {
		Patient patient = new Patient();
		patient.setGender("M");
		patient.setPatientId(2);
		// patient.setCreator(new User(1));
		// patient.setDateCreated date_created="2005-09-22 00:00:00.0"
		// changed_by="1" date_changed="2008-08-18 12:29:59.0"
		patient.addName(new PersonName("This", "Isa", "Test"));
		PatientIdentifier patientIdentifier = new PatientIdentifier("101-6", new PatientIdentifierType(1), new Location(1));
		patientIdentifier.setPreferred(true);
		patient.addIdentifier(patientIdentifier);
		Context.getPatientService().savePatient(patient);
	}