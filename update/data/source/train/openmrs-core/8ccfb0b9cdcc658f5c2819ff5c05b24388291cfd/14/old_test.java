@Test
	@Verifies(value = "should unvoid membership for every patient unvoided", method = "patientUnvoided(Patient, User, "
			+ "Date, String)")
	public void patientUnvoided_shouldUnvoidMemberships() throws Exception {
		executeDataSet(COHORT_XML);
		
		Cohort cohort = Context.getCohortService().getCohort(2);
		Patient unvoidedPatient = new Patient(7);
		User voidedBy = Context.getAuthenticatedUser();
		Date dateVoided = new Date();
		String voidReason = "Associated patient is voided";
		
		CohortMembership voidedMembership = new CohortMembership(unvoidedPatient);
		voidedMembership.setVoided(true);
		voidedMembership.setVoidedBy(voidedBy);
		voidedMembership.setDateVoided(dateVoided);
		voidedMembership.setVoidReason(voidReason);
		
		cohort.addMembership(voidedMembership);
		service.patientUnvoided(unvoidedPatient, voidedBy, dateVoided, voidReason);
		
		assertFalse(voidedMembership.getVoided());
		assertNull(voidedMembership.getVoidedBy());
		assertNull(voidedMembership.getDateVoided());
		assertNull(voidedMembership.getVoidReason());
	}