@Test
	public void patientVoided_shouldVoidMemberships() throws Exception {
		executeDataSet(COHORT_XML);

		Cohort cohort = Context.getCohortService().getCohort(2);
		Patient voidedPatient = new Patient(7);
		voidedPatient.setVoided(true);
		voidedPatient.setDateVoided(new Date());
		voidedPatient.setVoidedBy(Context.getAuthenticatedUser());
		voidedPatient.setVoidReason("Voided as a result of the associated patient getting voided");

		CohortMembership newMemberContainingVoidedPatient = new CohortMembership(voidedPatient.getPatientId());
		cohort.addMembership(newMemberContainingVoidedPatient);
		assertTrue(cohort.contains(voidedPatient));
		
		assertEquals(service.getCohortsContainingPatient(voidedPatient).size(), 1);

		service.patientVoided(voidedPatient);
		assertTrue(newMemberContainingVoidedPatient.getVoided());
		assertEquals(voidedPatient.getDateVoided(), newMemberContainingVoidedPatient.getDateVoided());
		assertEquals(voidedPatient.getVoidedBy(), newMemberContainingVoidedPatient.getVoidedBy());
		assertEquals(voidedPatient.getVoidReason(), newMemberContainingVoidedPatient.getVoidReason());
	}