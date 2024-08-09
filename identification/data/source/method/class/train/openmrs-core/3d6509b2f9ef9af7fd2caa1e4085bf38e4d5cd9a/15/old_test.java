	@Test
	public void startModule_shouldStartAllDependencies() {
		Module test1 = loadModule(MODULE1_PATH, MODULE1, true);
		Module test2 = loadModule(MODULE2_PATH, MODULE2, true);
		
		ModuleFactory.startModule(test2);
		
		Assert.assertNotNull(ModuleFactory.getStartedModuleById("test1")); // test1 should have been started, just by starting test2
		Assert.assertNotNull(ModuleFactory.getStartedModuleById("test2")); // should be started after starting all dependencies
		Assert.assertTrue(test1.isStarted());
		Assert.assertTrue(test2.isStarted());
	}