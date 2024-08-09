public static Module loadModule(Module module) throws ModuleException {
		
		log.debug("Adding module " + module.getName() + " to the module queue");
		
		Module oldModule = getLoadedModulesMap().get(module.getModuleId());
		if (oldModule != null) {
			// throw new ModuleException("A module with the same id already exists", module.getModuleId());
			// TODO need to stop the module in the web layer as well.
			unloadModule(oldModule);
		}
		
		getLoadedModulesMap().put(module.getModuleId(), module);
		
		return module;
	}