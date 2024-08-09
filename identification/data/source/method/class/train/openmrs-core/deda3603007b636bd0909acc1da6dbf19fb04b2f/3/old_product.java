public void setRequiredModules(List<String> requiredModules) {
		if (requiredModulesMap == null) {
			requiredModulesMap = new HashMap<String, String>();
		}
		
		for (String module : requiredModules) {
			requiredModulesMap.put(module, null);
		}
	}