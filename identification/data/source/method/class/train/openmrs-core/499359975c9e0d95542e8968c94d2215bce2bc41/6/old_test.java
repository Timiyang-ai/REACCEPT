	@Test
	public void loadModules_shouldNotCrashWhenFileIsNotFoundOrBroken() {
		ModuleFactory.unloadModule(ModuleFactory.getModuleById(MODULE1));
		String moduleLocation = ModuleUtil.class.getClassLoader().getResource(MODULE1_PATH).getPath();
		moduleLocation += "/i/broke/this/path/module.omod";
		File moduleToLoad = new File(moduleLocation);
		
		List<File> modulesToLoad = new ArrayList<>();
		modulesToLoad.add(moduleToLoad);
		ModuleFactory.loadModules(modulesToLoad);
		
		Assert.assertEquals(0, ModuleFactory.getLoadedModules().size());
	}