	@Test
	public void getPatient_shouldReturnNullObjectIfPatientIdDoesntExist() throws Exception {
		Assert.assertNull(Context.getPatientService().getPatient(1234512093));
	}