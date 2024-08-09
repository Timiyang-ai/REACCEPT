	@Test(expected = ModuleException.class)
	public void setStartupErrorMessage_shouldThrowExceptionWhenMessageIsNull() {
		testModule.setStartupErrorMessage(null);
	}