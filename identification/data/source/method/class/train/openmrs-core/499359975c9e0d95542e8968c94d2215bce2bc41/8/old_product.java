public static void loadModules() {
		
		// load modules from the user's module repository directory
		File modulesFolder = ModuleUtil.getModuleRepository();
		
		if (log.isDebugEnabled())
			log.debug("Loading modules from: " + modulesFolder.getAbsolutePath());
		
		if (modulesFolder.isDirectory()) {
			loadModules(Arrays.asList(modulesFolder.listFiles()));
		} else
			log.error("modules folder: '" + modulesFolder.getAbsolutePath() + "' is not a valid directory");
	}