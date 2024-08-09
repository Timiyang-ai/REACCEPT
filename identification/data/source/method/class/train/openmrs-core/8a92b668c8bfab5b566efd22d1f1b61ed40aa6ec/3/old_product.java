public static Module loadModule(Module module, Boolean replaceIfExists) throws ModuleException {
		
		if (log.isDebugEnabled())
			log.debug("Adding module " + module.getName() + " to the module queue");
		
		Module oldModule = getLoadedModulesMap().get(module.getModuleId());
		if (oldModule != null) {
			int versionComparison = ModuleUtil.compareVersion(oldModule.getVersion(), module.getVersion());
			if (versionComparison < 0) {
				// if oldModule version is lower, unload it and use the new
				unloadModule(oldModule);
			} else if (versionComparison == 0) {
				if (replaceIfExists) {
					// if the versions are the same and we're told to replaceIfExists, use the new
					unloadModule(oldModule);
				} else
					// if the versions are equal and we're not told to replaceIfExists, jump out of here in a bad way
					throw new ModuleException("A module with the same id and version already exists", module.getModuleId());
			} else {
				// if the older (already loaded) module is newer, keep that original one that was loaded. return that one.
				return oldModule;
			}
		}
		
		getLoadedModulesMap().put(module.getModuleId(), module);
		
		return module;
	}