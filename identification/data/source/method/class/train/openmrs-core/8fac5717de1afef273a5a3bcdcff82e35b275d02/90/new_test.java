	@Test
	public void triggerStateConversion_shouldSkipPastPatientProgramsThatAreAlreadyCompleted() throws InterruptedException {
		Integer patientProgramId = 1;
		PatientProgram pp = pws.getPatientProgram(patientProgramId);
		Date originalDateCompleted = new Date();
		pp.setDateCompleted(originalDateCompleted);
		pp = pws.savePatientProgram(pp);
		
		Concept diedConcept = cs.getConcept(16);
		//sanity check to ensure the patient died is a possible state in one of the work flows
		Assert.assertNotNull(pp.getProgram().getWorkflow(1).getState(diedConcept));
		
		Thread.sleep(10);//delay so that we have a time difference
		
		pp = pws.getPatientProgram(patientProgramId);
		Assert.assertEquals(originalDateCompleted, pp.getDateCompleted());
	}