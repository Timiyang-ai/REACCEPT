	@Test
	public void getPatientStateByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "ea89deaa-23cc-4840-92fe-63d199c37e4c";
		PatientState patientState = Context.getProgramWorkflowService().getPatientStateByUuid(uuid);
		Assert.assertEquals(1, (int) patientState.getPatientStateId());
	}