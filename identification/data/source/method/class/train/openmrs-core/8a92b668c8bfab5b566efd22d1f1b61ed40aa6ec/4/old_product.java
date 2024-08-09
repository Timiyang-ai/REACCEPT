public static Module loadModule(Module module) {
		
		log.debug("Adding module " + module.getName() + " to the module queue");
		
		if (getLoadedModulesMap().containsKey(module.getModuleId()))
			throw new ModuleException("A module with the same id already exists", module.getModuleId());
		
		getLoadedModulesMap().put(module.getModuleId(), module);
		
		return module;
	}