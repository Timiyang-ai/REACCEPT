public static void loadModules(List<File> modulesToLoad) {
		// loop over the modules and load all the modules that we can
		for (File f : modulesToLoad) {
			// ignore .svn folder and the like
			if (!f.getName().startsWith(".")) {
				try {
					Module mod = loadModule(f, true); // last module loaded wins
					log.debug("Loaded module: " + mod + " successfully");
				}
				catch (Exception e) {
					log.debug("Unable to load file in module directory: " + f + ". Skipping file.", e);
				}
			}
		}
		
		//inform modules, that they can't start before other modules
		Map<String, Module> loadedModulesMap = getLoadedModulesMapPackage();
		for (String key : loadedModules.keySet()) {
			Module m = loadedModules.get(key);
			Map<String, String> startBeforeModules = m.getStartBeforeModulesMap();
			if (startBeforeModules.size() > 0) {
				for (String s : startBeforeModules.keySet()) {
					Module mod = loadedModulesMap.get(s);
					if (mod != null)
						mod.addRequiredModule(m.getPackageName(), m.getVersion());
				}
			}
		}
	}