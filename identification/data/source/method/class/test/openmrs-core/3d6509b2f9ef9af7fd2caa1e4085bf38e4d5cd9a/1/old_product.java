public static Module startModule(Module module) throws ModuleException {
		
		if (module != null) {
			
			try {
			
				// check to be sure this module can run with our current version of OpenMRS code
				String requireVersion = module.getRequireOpenmrsVersion();
				if (requireVersion != null && !requireVersion.equals(""))
					if (ModuleUtil.compareVersion(OpenmrsConstants.OPENMRS_VERSION_SHORT, requireVersion) < 1)
						throw new ModuleException("Module's require_version ('" + requireVersion + "') does not match code version of '" + OpenmrsConstants.OPENMRS_VERSION_SHORT + "'", module.getName());
					
				// check to be sure this module can run with our current version of the OpenMRS database
				String requireDBVersion = module.getRequireDatabaseVersion();
				if (requireDBVersion != null && !requireDBVersion.equals(""))
					if (ModuleUtil.compareVersion(OpenmrsConstants.DATABASE_VERSION, requireDBVersion) < 1)
						throw new ModuleException("Module's require_database_version ('" + requireDBVersion + "') does not match code version of '" + OpenmrsConstants.DATABASE_VERSION + "'", module.getName());
				
				// check for required modules
				if (!requiredModulesStarted(module)) {
					throw new ModuleException("Not all required modules are started: (" + OpenmrsUtil.join(module.getRequiredModules(), ", ") + "). ", module.getName());
				}
				
				// fire up the classloader for this module
				ModuleClassLoader moduleClassLoader = new ModuleClassLoader(module, ModuleFactory.class.getClassLoader());
				getModuleClassLoaderMap().put(module, moduleClassLoader);
				
				// load the advice objects into the Context
				loadAdvice(module);
				
				// add all of this module's extensions to the extension map
				for (Extension ext : module.getExtensions()) {
					
					String extId = ext.getExtensionId();
					List<Extension> tmpExtensions = getExtensions(extId);
					if (tmpExtensions == null)
						tmpExtensions = new Vector<Extension>();
					
					log.debug("Adding to mapping ext: " + ext.getExtensionId() + " ext.class: " + ext.getClass());
					
					tmpExtensions.add(ext);
					getExtensionMap().put(extId, tmpExtensions);
				}
				
				// run the module's sql update script
				// This and the property updates are the only things that can't
				// be undone at startup, so put these calls after any other calls
				// that might hinder startup
				SortedMap<String, String> diffs = SqlDiffFileParser.getSqlDiffs(module);
				for (String version : diffs.keySet()) {
					String sql = diffs.get(version);
					runDiff(module, version, sql);
				}
				
				try {
					module.getActivator().startup();
				}
				catch (ModuleException e) {
					// just rethrow module exceptions.  This should be used for a module
					// marking that it had trouble starting
					throw e;
				}
				catch (Exception e) {
					throw new ModuleException("Error while calling module's Activator.startup() method", e);
				}
				
				// save the state of this module for future restarts
				Context.addProxyPrivilege("");
				AdministrationService as = Context.getAdministrationService();
				as.setGlobalProperty(module.getModuleId() + ".started", "true");
				Context.removeProxyPrivilege("");
				
				// effectively mark this module as started successfully
				getStartedModulesMap().put(module.getModuleId(), module);
				module.clearStartupError();
				
				// (this must be done after marking the module as started)
				// if this module defined any privileges or global properties, make 
				// sure they are added to the database
				// (Unfortunately, placing the call here will duplicate work done 
				//    at initial app startup)
				if (module.getPrivileges().size() > 0 || module.getGlobalProperties().size() > 0) {
					log.debug("Updating core dataset");
					Context.checkCoreDataset(); 
					// checkCoreDataset() currently doesn't throw an error.  If it did, it needs to be
					// caught and the module needs to be stopped and given a startup error
					
				}
				
			}
			catch (Exception e) {
				log.warn("Error while trying to start module: " + module.getModuleId(), e);
				module.setStartupErrorMessage(e.getMessage());
				
				// undo all of the actions in startup
				try {
					stopModule(module);
				}
				catch (Exception e2) {
					// this will probably occur about the same place as the error in startup
					log.debug("Error while stopping module: " + module.getModuleId(), e2);
				}
			}
			
		}
		
		return module;
	}