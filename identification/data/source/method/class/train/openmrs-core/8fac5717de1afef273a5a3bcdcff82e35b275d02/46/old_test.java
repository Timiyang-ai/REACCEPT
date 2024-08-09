	@Test
	public void removePatientFromCohort_shouldNotFailIfCohortDoesNotContainPatient() {
		executeDataSet(COHORT_XML);
		
		// make a patient
		Patient notInCohort = new Patient(4);
		// verify that the patient is not already in the Cohort
		assertFalse(service.getCohort(2).contains(notInCohort.getPatientId()));
		// try to remove it from the cohort without failing
		try {
			service.removePatientFromCohort(service.getCohort(2), notInCohort);
		}
		catch (Exception e) {
			Assert.fail("removePatientFromCohort(Cohort,Patient) should not fail if cohort doesn't contain patient");
		}
	}