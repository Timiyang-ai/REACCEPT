	@Test
	public void purgeProgram_shouldPurgeProgramWithPatientsEnrolled() {
		Program program = Context.getProgramWorkflowService().getProgram(2);
		
		// program has at least one patient enrolled
		List<PatientProgram> patientPrograms = Context.getProgramWorkflowService().getPatientPrograms(null, program, null,
		    null, null, null, true);
		assertTrue(patientPrograms.size() > 0);
		
		Context.getProgramWorkflowService().purgeProgram(program);
		
		// should cascade to patient programs
		for (PatientProgram patientProgram : patientPrograms) {
			assertNull(Context.getProgramWorkflowService().getPatientProgram(patientProgram.getId()));
		}
		// make sure that the program was deleted properly
		assertNull(Context.getProgramWorkflowService().getProgram(2));
	}