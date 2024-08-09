	@Test
	public void patientVoided_shouldVoidMemberships() {
		executeDataSet(COHORT_XML);
		
		Cohort cohort = Context.getCohortService().getCohort(2);
		Patient voidedPatient = new Patient(7);
		voidedPatient.setVoided(true);
		voidedPatient.setDateVoided(new Date());
		voidedPatient.setVoidedBy(Context.getAuthenticatedUser());
		voidedPatient.setVoidReason("Voided as a result of the associated patient getting voided");
		
		CohortMembership newMemberContainingVoidedPatient = new CohortMembership(voidedPatient.getPatientId());
		cohort.addMembership(newMemberContainingVoidedPatient);
		assertTrue(cohort.contains(voidedPatient.getPatientId()));
		
		assertEquals(1, service.getCohortsContainingPatientId(voidedPatient.getId()).size());
		
		service.notifyPatientVoided(voidedPatient);
		assertTrue(newMemberContainingVoidedPatient.getVoided());
		assertEquals(newMemberContainingVoidedPatient.getDateVoided(), voidedPatient.getDateVoided());
		assertEquals(newMemberContainingVoidedPatient.getVoidedBy(), voidedPatient.getVoidedBy());
		assertEquals(newMemberContainingVoidedPatient.getVoidReason(), voidedPatient.getVoidReason());
	}