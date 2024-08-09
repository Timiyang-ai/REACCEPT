public static List<String> getMandatoryModules() {
		
		List<String> mandatoryModuleIds = new ArrayList<String>();
		
		List<GlobalProperty> props = Context.getAdministrationService().getGlobalPropertiesBySuffix(".mandatory");
		
		for (GlobalProperty prop : props) {
			if ("true".equalsIgnoreCase(prop.getPropertyValue())) {
				mandatoryModuleIds.add(prop.getProperty().replace(".mandatory", ""));
			}
		}
		
		return mandatoryModuleIds;
	}