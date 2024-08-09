protected static void checkMandatoryModulesStarted() throws ModuleException {
		
		List<String> mandatoryModuleIds = getMandatoryModules();
		Set<String> startedModuleIds = ModuleFactory.getStartedModulesMap().keySet();
		
		mandatoryModuleIds.removeAll(startedModuleIds);
		
		// any module ids left in the list are not started
		if (!mandatoryModuleIds.isEmpty()) {
			throw new MandatoryModuleException(mandatoryModuleIds);
		}
	}