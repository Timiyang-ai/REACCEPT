@Test
	public void getCohortsContainingPatient_shouldReturnCohortsThatHaveGivenPatient() throws Exception {
		executeDataSet(COHORT_XML);
		
		Patient patientToAdd = new Patient(7);
		service.addPatientToCohort(service.getCohort(2), patientToAdd);
		assertTrue(service.getCohort(2).contains(patientToAdd.getPatientId()));
		
		List<Cohort> cohortsWithGivenPatient = service.getCohortsContainingPatient(patientToAdd);
		assertTrue(cohortsWithGivenPatient.contains(service.getCohort(2)));
	}