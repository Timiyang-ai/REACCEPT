	@Test
	public void getCohortsContainingPatient_shouldNotReturnVoidedCohorts() {
		executeDataSet(COHORT_XML);
		
		// make sure we have two cohorts, the first of which is voided
		assertTrue(service.getCohort(1).getVoided());
		assertFalse(service.getCohort(2).getVoided());
		
		// add a patient to both cohorts
		Patient patientToAdd = new Patient(7);
		service.addPatientToCohort(service.getCohort(1), patientToAdd);
		service.addPatientToCohort(service.getCohort(2), patientToAdd);
		assertTrue(service.getCohort(1).contains(patientToAdd.getPatientId()));
		assertTrue(service.getCohort(2).contains(patientToAdd.getPatientId()));
		
		// call the method and it should not return the voided cohort
		List<Cohort> cohortsWithPatientAdded = service.getCohortsContainingPatientId(patientToAdd.getId());
		assertNotNull(cohortsWithPatientAdded);
		assertFalse(cohortsWithPatientAdded.contains(service.getCohort(1)));
		
	}