@Test
	@Verifies(value = "should save cohort after removing patient", method = "removePatientFromCohort(Cohort,Patient)")
	public void removePatientFromCohort_shouldSaveCohortAfterRemovingPatient() throws Exception {
		executeDataSet(COHORT_XML);
		
		// make a patient, add it using the method
		Patient patientToAddThenRemove = new Patient(4);
		service.addPatientToCohort(service.getCohort(2), patientToAddThenRemove);
		assertTrue(service.getCohort(2).contains(patientToAddThenRemove));
		service.removePatientFromCohort(service.getCohort(2), patientToAddThenRemove);
		assertFalse(service.getCohort(2).contains(patientToAddThenRemove));
	}