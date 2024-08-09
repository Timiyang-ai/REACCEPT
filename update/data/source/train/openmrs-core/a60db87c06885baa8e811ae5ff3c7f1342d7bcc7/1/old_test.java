@Test
	@Verifies(value = "should return all registered system variables", method = "getSystemVariables()")
	public void getSystemVariables_shouldReturnAllRegisteredSystemVariables() throws Exception {
		// The method implementation adds 12 system variables
		Assert.assertEquals(12, Context.getAdministrationService().getSystemVariables().size());
	}