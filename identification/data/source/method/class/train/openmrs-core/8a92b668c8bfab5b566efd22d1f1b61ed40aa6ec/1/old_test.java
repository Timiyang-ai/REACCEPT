	@Test
	public void loadModule_shouldLoadModuleIfItIsCurrentlyNotLoaded() {
		Module test2 = loadModule(MODULE2_PATH, MODULE2, false);
		
		//verify that module test2 is started
		ModuleFactory.startModule(test2);
		Assert.assertTrue(ModuleFactory.getLoadedModules().contains(test2));
	}