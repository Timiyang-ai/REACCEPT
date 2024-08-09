@Test
	public void removePatientFromCohort_shouldNotFailIfCohortDoesNotContainPatient() throws Exception {
		executeDataSet(COHORT_XML);
		
		// make a patient
		Patient patientToAddThenRemove = new Patient(4);
		// verify that the patient is not already in the Cohort
		assertFalse(service.getCohort(2).contains(patientToAddThenRemove));
		// try to remove it from the cohort without failing
		try {
			service.removePatientFromCohort(service.getCohort(2), patientToAddThenRemove);
		}
		catch (Exception e) {
			Assert.fail("removePatientFromCohort(Cohort,Patient) should not fail if cohort doesn't contain patient");
		}
	}