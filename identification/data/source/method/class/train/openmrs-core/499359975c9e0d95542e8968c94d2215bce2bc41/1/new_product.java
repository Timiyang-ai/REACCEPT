public static void loadModules(List<File> modulesToLoad) {
		// loop over the modules and load all the modules that we can
		for (File f : modulesToLoad) {
			// ignore .svn folder and the like
			if (!f.getName().startsWith(".")) {
				try {
					Module mod = loadModule(f, true); // last module loaded wins
					log.debug("Loaded module: " + mod + " successfully");
				}
				catch (Throwable t) {
					log.debug("Unable to load file in module directory: " + f + ". Skipping file.", t);
				}
			}
		}
	}