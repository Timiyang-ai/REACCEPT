public static List<String> getMandatoryModules() {
		
		List<String> mandatoryModuleIds = new ArrayList<>();
		
		try {
			List<GlobalProperty> props = Context.getAdministrationService().getGlobalPropertiesBySuffix(".mandatory");
			
			for (GlobalProperty prop : props) {
				if ("true".equalsIgnoreCase(prop.getPropertyValue())) {
					mandatoryModuleIds.add(prop.getProperty().replace(".mandatory", ""));
				}
			}
		}
		catch (Exception e) {
			log.warn("Unable to get the mandatory module list", e);
		}
		
		return mandatoryModuleIds;
	}