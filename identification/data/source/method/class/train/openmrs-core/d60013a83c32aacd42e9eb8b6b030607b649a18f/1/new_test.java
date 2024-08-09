	@Test(expected = ModuleException.class)
	public void startOpenmrs_shouldThrowExceptionGivenNull() throws DatabaseUpdateException, InputRequiredException {
		WebDaemon.startOpenmrs(null);
	}