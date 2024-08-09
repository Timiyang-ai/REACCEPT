	@Test
	public void compareTo_shouldReturnPositiveIfStartDatesEqualAndThisEndDateNull() {
		//given
		PatientState patientState = new PatientState();
		patientState.setStartDate(leftRange);
		patientState.setEndDate(null);
		patientState.setVoided(false);
		
		PatientState patientState2 = new PatientState();
		patientState2.setStartDate(leftRange);
		patientState2.setEndDate(rightRange);
		patientState2.setVoided(false);
		
		//when
		int result = patientState.compareTo(patientState2);
		
		//then
		Assert.assertTrue(result > 0);
	}