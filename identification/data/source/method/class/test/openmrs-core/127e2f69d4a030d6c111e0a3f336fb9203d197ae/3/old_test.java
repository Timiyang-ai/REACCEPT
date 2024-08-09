	@Test
	public void validate_shouldFailIfThePatientsForTheVisitAndTheEncounterDontMatch() {
		
		encounter.setPatient(new Patient(2));
		Visit visit = new Visit();
		visit.setPatient(new Patient(3));
		encounter.setVisit(visit);
		
		encounterValidator.validate(encounter, errors);
		
		Assert.assertEquals("Encounter.visit.patients.dontMatch", errors.getFieldError("visit").getCode());
	}