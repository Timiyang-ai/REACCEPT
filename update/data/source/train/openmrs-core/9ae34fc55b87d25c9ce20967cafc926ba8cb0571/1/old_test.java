@Test
	public void addPatientToCohort_shouldNotFailIfCohortAlreadyContainsPatient() {
		executeDataSet(COHORT_XML);
		
		// make a patient, add it using the method
		Patient patientToAdd = new Patient(4);
		service.addPatientToCohort(service.getCohort(2), patientToAdd);
		assertTrue(service.getCohort(2).contains(4));
		
		// do it again to see if it fails
		try {
			service.addPatientToCohort(service.getCohort(2), patientToAdd);
		}
		catch (Exception e) {
			Assert.fail("addPatientToCohort(Cohort,Patient) fails when cohort already contains patient.");
		}
	}