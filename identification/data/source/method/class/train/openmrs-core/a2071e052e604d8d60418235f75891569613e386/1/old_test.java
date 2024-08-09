	@Test
	public void getSortedStates_shouldReturnAllStatesEvenIfTwoHaveIdenticalStartAndEndDates() throws Exception {
		
		// this test written specifically to verify fix for https://tickets.openmrs.org/browse/TRUNK-3645
		Method getSortedStates = PatientProgram.class.getDeclaredMethod("getSortedStates");
		getSortedStates.setAccessible(true);
		Assert.assertNotNull(getSortedStates);
		
		Set<PatientState> patientStates = new HashSet<>();
		PatientState patientState = new PatientState();
		patientState.setStartDate(date);
		patientState.setEndDate(laterDate);
		patientState.setVoided(false);
		patientStates.add(patientState);
		
		PatientState patientState2 = new PatientState();
		patientState2.setStartDate(date);
		patientState2.setEndDate(laterDate);
		patientState2.setVoided(false);
		patientStates.add(patientState2);
		
		PatientState patientState3 = new PatientState();
		patientState3.setStartDate(earlierDate);
		patientState3.setEndDate(date);
		patientState3.setVoided(false);
		patientStates.add(patientState3);
		
		PatientProgram program = new PatientProgram();
		program.setStates(patientStates);
		
		// when
		List<PatientState> sortedStates = (List<PatientState>) getSortedStates.invoke(program);
		
		// then
		Assert.assertEquals(3, sortedStates.size());
		
	}