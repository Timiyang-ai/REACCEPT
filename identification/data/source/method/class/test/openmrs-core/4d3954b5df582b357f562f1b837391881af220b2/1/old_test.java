	@Test(expected = IllegalArgumentException.class)
	public void handle_shouldThrowIllegalArgumentExceptionIfPatientVoidReasonIsNull() {
		Patient p = Context.getPatientService().getPatient(2);
		Context.getPatientService().voidPatient(p, null);
	}