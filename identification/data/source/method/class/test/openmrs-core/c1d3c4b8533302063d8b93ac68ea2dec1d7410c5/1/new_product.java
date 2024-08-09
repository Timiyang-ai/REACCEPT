static boolean shouldResourceBeIncluded(Module module, URL fileUrl, String openmrsVersion,
	        Map<String, String> startedRelatedModules) {
		boolean include = true; //all resources are included by default
		
		for (ModuleConditionalResource conditionalResource : module.getConditionalResources()) {
			if (fileUrl.getPath().matches(".*" + conditionalResource.getPath() + "$")) {
				include = false; //if a resource matches a path of contidionalResource then it must meet all conditions
				
				if (StringUtils.isNotBlank(conditionalResource.getOpenmrsPlatformVersion())) { //openmrsPlatformVersion is optional
					include = ModuleUtil.matchRequiredVersions(openmrsVersion, conditionalResource
					        .getOpenmrsPlatformVersion());
					
					if (!include) {
						return false;
					}
				}
				
				if (conditionalResource.getModules() != null) { //modules are optional
					for (ModuleConditionalResource.ModuleAndVersion conditionalModuleResource : conditionalResource
					        .getModules()) {
						String moduleVersion = startedRelatedModules.get(conditionalModuleResource.getModuleId());
						if (moduleVersion != null) {
							include = ModuleUtil
							        .matchRequiredVersions(moduleVersion, conditionalModuleResource.getVersion());
							
							if (!include) {
								return false;
							}
						}
					}
					
				}
			}
		}
		
		return include;
	}