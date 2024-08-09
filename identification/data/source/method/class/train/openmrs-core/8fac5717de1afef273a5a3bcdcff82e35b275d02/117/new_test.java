	@Test
	public void getActive_shouldReturnFalseIfVoidedAndDateInRange() {
		//given
		PatientState patientState = new PatientState();
		patientState.setStartDate(leftRange);
		patientState.setEndDate(rightRange);
		patientState.setVoided(true);
		
		//when
		boolean active = patientState.getActive(inRange);
		
		//then
		Assert.assertFalse(active);
	}