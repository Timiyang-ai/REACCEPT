public static Module startModule(Module module) throws ModuleException {
		
		if (module != null) {
			
			String moduleId = module.getModuleId();
			try {
				
				// check to be sure this module can run with our current version
				// of OpenMRS code
				String requireVersion = module.getRequireOpenmrsVersion();
				if (requireVersion != null && !requireVersion.equals(""))
					if (!ModuleUtil.matchRequiredVersions(OpenmrsConstants.OPENMRS_VERSION_SHORT, requireVersion))
						throw new ModuleException("Module requires at least version '" + requireVersion
						        + "'.  Current code version is only '" + OpenmrsConstants.OPENMRS_VERSION_SHORT + "'",
						        module.getName());
				
				// check for required modules
				if (!requiredModulesStarted(module)) {
					throw new ModuleException("Not all required modules are started: "
					        + OpenmrsUtil.join(getMissingRequiredModules(module), ", ") + ". ", module.getName());
				}
				
				// fire up the classloader for this module
				ModuleClassLoader moduleClassLoader = new ModuleClassLoader(module, ModuleFactory.class.getClassLoader());
				getModuleClassLoaderMap().put(module, moduleClassLoader);
				
				// don't load the advice objects into the Context
				// At startup, the spring context isn't refreshed until all modules
				// have been loaded.  This causes errors if called here during a 
				// module's startup if one of these advice points is on another 
				// module because that other module's service won't have been loaded
				// into spring yet.  All advice for all modules must be reloaded 
				// a spring context refresh anyway, so skip the advice loading here
				// loadAdvice(module);
				
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
				// be undone at startup, so put these calls after any other
				// calls that might hinder startup
				SortedMap<String, String> diffs = SqlDiffFileParser.getSqlDiffs(module);
				
				try {
					// this method must check and run queries against the database.
					// to do this, it must be "authenticated".  Give the current 
					// "user" the proxy privilege so this can be done. ("user" might
					// be nobody because this is being run at startup)
					Context.addProxyPrivilege("");
					
					for (String version : diffs.keySet()) {
						String sql = diffs.get(version);
						if (StringUtils.hasText(sql))
							runDiff(module, version, sql);
					}
				}
				finally {
					// take the "authenticated" privilege away from the current "user"
					Context.removeProxyPrivilege("");
				}
				
				// effectively mark this module as started successfully
				getStartedModulesMap().put(moduleId, module);
				
				try {
					// save the state of this module for future restarts
					saveGlobalProperty(moduleId + ".started", "true", getGlobalPropertyStartedDescription(moduleId));
					
					// save the mandatory status
					saveGlobalProperty(moduleId + ".mandatory", String.valueOf(module.isMandatory()),
					    getGlobalPropertyMandatoryModuleDescription(moduleId));
				}
				catch (Exception e) {
					// pass over errors because this doesn't really concern startup
					// passing over this also allows for multiple of the same-named modules
					// to be loaded in junit tests that are run within one session
					log.debug("Got an error when trying to set the global property on module startup", e);
				}
				
				// (this must be done after putting the module in the started
				// list)
				// if this module defined any privileges or global properties,
				// make sure they are added to the database
				// (Unfortunately, placing the call here will duplicate work
				// done at initial app startup)
				if (module.getPrivileges().size() > 0 || module.getGlobalProperties().size() > 0) {
					log.debug("Updating core dataset");
					Context.checkCoreDataset();
					// checkCoreDataset() currently doesn't throw an error. If
					// it did, it needs to be
					// caught and the module needs to be stopped and given a
					// startup error
				}
				
				// should be near the bottom so the module has all of its stuff
				// set up for it already.
				try {
					module.getActivator().startup();
				}
				catch (ModuleException e) {
					// just rethrow module exceptions. This should be used for a
					// module marking that it had trouble starting
					throw e;
				}
				catch (Exception e) {
					throw new ModuleException("Error while calling module's Activator.startup() method", e);
				}
				
				// erase any previous startup error
				module.clearStartupError();
				
			}
			catch (Exception e) {
				log.warn("Error while trying to start module: " + moduleId, e);
				module.setStartupErrorMessage("Error while trying to start module", e);
				
				// undo all of the actions in startup
				try {
					boolean skipOverStartedProperty = false;
					
					if (e instanceof MandatoryModuleException)
						skipOverStartedProperty = true;
					
					stopModule(module, skipOverStartedProperty, true);
				}
				catch (Exception e2) {
					// this will probably occur about the same place as the
					// error in startup
					log.debug("Error while stopping module: " + moduleId, e2);
				}
			}
			
		}
		
		// refresh spring service context?
		
		return module;
	}