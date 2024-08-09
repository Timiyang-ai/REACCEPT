@Test
	public void patientUnvoided_shouldUnvoidMemberships() throws Exception {
		executeDataSet(COHORT_XML);
		
		Cohort cohort = Context.getCohortService().getCohort(2);
		Patient unvoidedPatient = new Patient(7);
		User voidedBy = Context.getAuthenticatedUser();
		Date dateVoided = new Date();
		String voidReason = "Associated patient is voided";
		
		CohortMembership voidedMembership = new CohortMembership(unvoidedPatient.getPatientId());
		cohort.addMembership(voidedMembership);
		voidedMembership.setVoided(true);
		voidedMembership.setVoidedBy(voidedBy);
		voidedMembership.setDateVoided(dateVoided);
		voidedMembership.setVoidReason(voidReason);
		
		service.patientUnvoided(unvoidedPatient, voidedBy, dateVoided);
		
		assertFalse(voidedMembership.getVoided());
		assertNull(voidedMembership.getVoidedBy());
		assertNull(voidedMembership.getDateVoided());
		assertNull(voidedMembership.getVoidReason());
	}