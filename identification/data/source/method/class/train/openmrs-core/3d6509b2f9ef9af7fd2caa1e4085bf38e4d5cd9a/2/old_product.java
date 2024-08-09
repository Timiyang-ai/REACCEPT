public static Module startModule(Module module, boolean isOpenmrsStartup,
	        AbstractRefreshableApplicationContext applicationContext) throws ModuleException {
		
		if (!requiredModulesStarted(module)) {
			int missingModules = 0;
			
			for (String packageName : module.getRequiredModulesMap().keySet()) {
				Module mod = getModuleByPackage(packageName);
				
				// mod not installed
				if (mod == null) {
					missingModules++;
					continue;
				}
				
				if (!mod.isStarted())
					startModule(mod);
			}
			
			if (missingModules > 0) {
				String message = getFailedToStartModuleMessage(module);
				log.error(message);
				module.setStartupErrorMessage(message);
				notifySuperUsersAboutModuleFailure(module);
				return module; // instead of return null, i realized that Daemon.startModule() always returns a Module object,irrespective of whether the startup succeeded  
			}
		}
		return Daemon.startModule(module, isOpenmrsStartup, applicationContext);
	}