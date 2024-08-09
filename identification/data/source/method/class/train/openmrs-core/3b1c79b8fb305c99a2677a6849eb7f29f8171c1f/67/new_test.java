	@Test
	public void getPatientProgramByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "2edf272c-bf05-4208-9f93-2fa213ed0415";
		PatientProgram patientProgram = Context.getProgramWorkflowService().getPatientProgramByUuid(uuid);
		Assert.assertEquals(2, (int) patientProgram.getPatientProgramId());
	}