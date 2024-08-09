public void setRequiredModules(List<String> requiredModules) {
		if (requiredModulesMap == null) {
			requiredModulesMap = new HashMap<>();
		}
		
		for (String module : requiredModules) {
			requiredModulesMap.put(module, null);
		}
	}