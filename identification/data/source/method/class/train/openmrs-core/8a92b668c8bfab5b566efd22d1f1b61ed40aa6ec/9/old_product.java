public static Module loadModule(Module module, Boolean replaceIfExists) throws ModuleException {
		
		if (log.isDebugEnabled())
			log.debug("Adding module " + module.getName() + " to the module queue");
		
		Module oldModule = getLoadedModulesMap().get(module.getModuleId());
		if (oldModule != null) {
			if (replaceIfExists == true) {
				// TODO need to stop the module in the web layer as well.
				unloadModule(oldModule);
			} else
				throw new ModuleException("A module with the same id already exists", module.getModuleId());
		}
		
		getLoadedModulesMap().put(module.getModuleId(), module);
		
		return module;
	}