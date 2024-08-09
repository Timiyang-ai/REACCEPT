@Test
	@Verifies(value = "should save cohort after removing patient", method = "removePatientFromCohort(Cohort,Patient)")
	public void removePatientFromCohort_shouldSaveCohortAfterRemovingPatient() throws Exception {
		executeDataSet(COHORT_XML);
		
		// make a patient, add it using the method
		Patient patientToAddThenRemove = new Patient(4);
		service.addPatientToCohort(service.getCohort(2), patientToAddThenRemove);
		assertTrue(service.getCohort(2).contains(patientToAddThenRemove.getPatientId()));
		service.removePatientFromCohort(service.getCohort(2), patientToAddThenRemove);
		List<CohortMembership> memberList = service.getCohort(2)
				.getMembers().stream()
				.filter(m -> m.getPatient().getPatientId().equals(patientToAddThenRemove.getPatientId()))
				.collect(Collectors.toList());
		CohortMembership memberWithPatientToRemove = memberList.get(0);
		assertNotNull(memberWithPatientToRemove.getEndDate());
	}