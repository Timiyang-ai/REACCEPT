	@Test
	public void getService_shouldReturnTheSameObjectWhenCalledMultipleTimesForTheSameClass() {
		PatientService ps1 = Context.getService(PatientService.class);
		PatientService ps2 = Context.getService(PatientService.class);
		Assert.assertTrue(ps1 == ps2);
	}