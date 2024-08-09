@Test
	@Verifies(value = "should return all registered system variables", method = "getSystemVariables()")
	public void getSystemVariables_shouldReturnAllRegisteredSystemVariables() throws Exception {
		// The method implementation adds 11 system variables
		Assert.assertEquals(11, Context.getAdministrationService().getSystemVariables().size());
	}