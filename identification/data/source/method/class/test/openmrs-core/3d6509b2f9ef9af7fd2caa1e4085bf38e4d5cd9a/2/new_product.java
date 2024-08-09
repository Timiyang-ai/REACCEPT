public static Module startModule(Module module) throws ModuleException {

		if (module != null) {

			try {

				// check to be sure this module can run with our current version
				// of OpenMRS code
				String requireVersion = module.getRequireOpenmrsVersion();
				if (requireVersion != null && !requireVersion.equals(""))
					if (ModuleUtil.compareVersion(
					        OpenmrsConstants.OPENMRS_VERSION_SHORT,
					        requireVersion) < 0)
						throw new ModuleException(
						        "Module requires at least version '"
						                + requireVersion
						                + "'.  Current code version is only '"
						                + OpenmrsConstants.OPENMRS_VERSION_SHORT
						                + "'", module.getName());

				// check to be sure this module can run with our current version
				// of the OpenMRS database
				String requireDBVersion = module.getRequireDatabaseVersion();
				if (requireDBVersion != null && !requireDBVersion.equals(""))
					if (ModuleUtil
					        .compareVersion(OpenmrsConstants.DATABASE_VERSION,
					                requireDBVersion) < 0)
						throw new ModuleException(
						        "Module requires at least database version '"
						                + requireDBVersion
						                + "'. Current database version is only '"
						                + OpenmrsConstants.DATABASE_VERSION
						                + "'", module.getName());

				// check for required modules
				if (!requiredModulesStarted(module)) {
					throw new ModuleException(
					        "Not all required modules are started: "
					                + OpenmrsUtil.join(module
					                        .getRequiredModules(), ", ") + ". ",
					        module.getName());
				}

				// fire up the classloader for this module
				ModuleClassLoader moduleClassLoader = new ModuleClassLoader(
				        module, ModuleFactory.class.getClassLoader());
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

					log.debug("Adding to mapping ext: " + ext.getExtensionId()
					        + " ext.class: " + ext.getClass());

					tmpExtensions.add(ext);
					getExtensionMap().put(extId, tmpExtensions);
				}

				// run the module's sql update script
				// This and the property updates are the only things that can't
				// be undone at startup, so put these calls after any other
				// calls that might hinder startup
				SortedMap<String, String> diffs = SqlDiffFileParser
				        .getSqlDiffs(module);
				
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
				getStartedModulesMap().put(module.getModuleId(), module);

				try {
					// save the state of this module for future restarts
					Context.addProxyPrivilege("");
					AdministrationService as = Context.getAdministrationService();
					GlobalProperty gp = new GlobalProperty(module.getModuleId()
					        + ".started", "true",
					        getGlobalPropertyStartedDescription(module
					                .getModuleId()));
					as.setGlobalProperty(gp);
				}
				finally {
					Context.removeProxyPrivilege("");
				}

				// (this must be done after putting the module in the started
				// list)
				// if this module defined any privileges or global properties,
				// make sure they are added to the database
				// (Unfortunately, placing the call here will duplicate work
				// done at initial app startup)
				if (module.getPrivileges().size() > 0
				        || module.getGlobalProperties().size() > 0) {
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
				} catch (ModuleException e) {
					// just rethrow module exceptions. This should be used for a
					// module marking that it had trouble starting
					throw e;
				} catch (Exception e) {
					throw new ModuleException(
					        "Error while calling module's Activator.startup() method",
					        e);
				}

				// erase any previous startup error
				module.clearStartupError();

			} catch (Exception e) {
				log.warn("Error while trying to start module: "
				        + module.getModuleId(), e);
				module.setStartupErrorMessage("Error while trying to start module: " + e.getMessage());

				// undo all of the actions in startup
				try {
					stopModule(module);
				} catch (Exception e2) {
					// this will probably occur about the same place as the
					// error in startup
					log.debug("Error while stopping module: "
					        + module.getModuleId(), e2);
				}
			}

		}

		// refresh spring service context?

		return module;
	}