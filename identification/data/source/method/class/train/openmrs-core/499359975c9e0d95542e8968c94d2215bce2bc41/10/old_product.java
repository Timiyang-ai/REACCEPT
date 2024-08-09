public static void loadModules() {

		// load modules from the user's module repository directory
		File modulesFolder = ModuleUtil.getModuleRepository();
		
		if (log.isDebugEnabled())
			log.debug("Loading modules from: " + modulesFolder.getAbsolutePath());

		if (modulesFolder.isDirectory()) {
			// loop over the modules and load the modules that we can
			for (File f : modulesFolder.listFiles()) {
				if (!f.getName().startsWith(".")) { // ignore .svn folder and
													// the like
					Module mod = loadModule(f);
					log.debug("Loaded module: " + mod + " successfully");
				}
			}
		} else
			log.error("modules folder: '" + modulesFolder.getAbsolutePath()
			        + "' is not a valid directory");
	}