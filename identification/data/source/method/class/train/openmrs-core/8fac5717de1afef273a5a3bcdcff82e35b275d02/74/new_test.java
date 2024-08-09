	@Test
	public void addPatientToCohort_shouldAddAPatientAndSaveTheCohort() {
		executeDataSet(COHORT_XML);
		
		// make a patient, add it using the method
		Patient patientToAdd = Context.getPatientService().getPatient(3);
		service.addPatientToCohort(service.getCohort(2), patientToAdd);
		// proof of "save the cohort": see if the patient is in the cohort
		assertTrue(service.getCohort(2).contains(3));
	}