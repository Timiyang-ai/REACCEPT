	@Test
	public void retrieveFilterDefinitions_shouldThrowModuleExceptionIfNoConfig() {
		expectedException.expect(ModuleException.class);
		expectedException.expectMessage("Unable to parse filters in module configuration.");
		Module module = new Module("test");
		ModuleFilterDefinition.retrieveFilterDefinitions(module);
	}