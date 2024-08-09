	@Test
	public void init() throws Exception {
		verify(functionManager).registerFunction(ExternalLogic.PERM_ADMIN);
		verify(functionManager).registerFunction(ExternalLogic.PERM_SEND);
	}