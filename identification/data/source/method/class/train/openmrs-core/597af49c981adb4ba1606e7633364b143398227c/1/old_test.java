@Test
	@Verifies(value = "should fail if the patients for the visit and the encounter dont match", method = "validate(Object,Errors)")
	public void validate_shouldFailIfThePatientsForTheVisitAndTheEncounterDontMatch() throws Exception {
		Encounter encounter = new Encounter();
		encounter.setPatient(new Patient(2));
		Visit visit = new Visit();
		visit.setPatient(new Patient(3));
		encounter.setVisit(visit);
		Errors errors = new BindException(encounter, "encounter");
		new EncounterValidator().validate(encounter, errors);
		Assert.assertTrue(errors.hasFieldErrors("visit"));
	}