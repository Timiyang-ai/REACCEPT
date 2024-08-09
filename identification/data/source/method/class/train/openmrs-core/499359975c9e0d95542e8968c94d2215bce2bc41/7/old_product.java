public static void loadModules(List<File> modulesToLoad) {
		for (File f : modulesToLoad) {
			Module mod = loadModule(f);
			log.debug("Loaded module: " + mod + " successfully");
		}
	}