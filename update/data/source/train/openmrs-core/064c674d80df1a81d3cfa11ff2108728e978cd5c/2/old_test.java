@Test
	public void getCohortsContainingPatient_shouldNotReturnMembershipsOutsideSpecifiedDate() throws Exception {
		executeDataSet(COHORT_XML);
		
		Cohort cohort = service.getCohort(2);
		
		Patient patient = new Patient(7);
		CohortMembership membership = new CohortMembership(patient.getPatientId());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = dateFormat.parse("2017-01-01 00:00:00");
		membership.setStartDate(startDate);
		cohort.addMembership(membership);
		assertTrue(cohort.contains(patient.getPatientId()));
		
		Date date = dateFormat.parse("2016-01-01 00:00:00");
		assertEquals(0, service.getCohortsContainingPatient(patient, false, date).size());
	}