public static void loadModules() {
		
		// load modules from the user's module repository directory
		File modulesFolder = ModuleUtil.getModuleRepository();
		
		if (log.isDebugEnabled()) {
			log.debug("Loading modules from: " + modulesFolder.getAbsolutePath());
		}
		
		File[] files = modulesFolder.listFiles();
		if (modulesFolder.isDirectory() && files != null) {
			loadModules(Arrays.asList(files));
		} else {
			log.error("modules folder: '" + modulesFolder.getAbsolutePath() + "' is not a directory or IO error occurred");
		}
	}