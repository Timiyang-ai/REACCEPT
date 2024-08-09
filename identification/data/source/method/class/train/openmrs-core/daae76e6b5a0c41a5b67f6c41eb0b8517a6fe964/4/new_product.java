public Module parse() throws ModuleException {
		
		Module module;
		JarFile jarfile = null;
		InputStream configStream = null;
		
		try {
			try {
				jarfile = new JarFile(moduleFile);
			}
			catch (IOException e) {
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.cannotGetJarFile"),
				        moduleFile.getName(), e);
			}
			
			// look for config.xml in the root of the module
			ZipEntry config = jarfile.getEntry("config.xml");
			if (config == null) {
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.noConfigFile"),
				        moduleFile.getName());
			}
			
			// get a config file stream
			try {
				configStream = jarfile.getInputStream(config);
			}
			catch (IOException e) {
				throw new ModuleException(Context.getMessageSourceService().getMessage(
				    "Module.error.cannotGetConfigFileStream"), moduleFile.getName(), e);
			}
			Document configDoc = parseConfig(configStream);

			Element rootNode = configDoc.getDocumentElement();
			
			String configVersion = rootNode.getAttribute("configVersion").trim();
			
			if (!validConfigVersions.contains(configVersion)) {
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.invalidConfigVersion",
				    new Object[] { configVersion, String.join(", ", validConfigVersions) }, Context.getLocale()), moduleFile.getName());
			}
			
			String name = getElement(rootNode, "name").trim();
			String moduleId = getElement(rootNode,"id").trim();
			String packageName = getElement(rootNode,"package").trim();
			String author = getElement(rootNode,"author").trim();
			String desc = getElement(rootNode, "description").trim();
			String version = getElement(rootNode, "version").trim();
			
			// do some validation
			if (name == null || name.length() == 0) {
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.nameCannotBeEmpty"),
				        moduleFile.getName());
			}
			if (moduleId == null || moduleId.length() == 0) {
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.idCannotBeEmpty"), name);
			}
			if (packageName == null || packageName.length() == 0) {
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.packageCannotBeEmpty"),
				        name);
			}
			
			// create the module object
			module = new Module(name, moduleId, packageName, author, desc, version);
			
			// find and load the activator class
			module.setActivatorName(getElement(rootNode, "activator").trim());
			
			module.setRequireDatabaseVersion(getElement(rootNode, "require_database_version").trim());
			module.setRequireOpenmrsVersion(getElement(rootNode, "require_version").trim());
			module.setUpdateURL(getElement(rootNode, "updateURL").trim());
			module.setRequiredModulesMap(getRequiredModules(rootNode));
			module.setAwareOfModulesMap(getAwareOfModules(rootNode));
			module.setStartBeforeModulesMap(getStartBeforeModules(rootNode));
			
			module.setAdvicePoints(getAdvice(rootNode,  module));
			module.setExtensionNames(getExtensions(rootNode));
			
			module.setPrivileges(getPrivileges(rootNode));
			module.setGlobalProperties(getGlobalProperties(rootNode));
			
			module.setMappingFiles(getMappingFiles(rootNode));
			module.setPackagesWithMappedClasses(getPackagesWithMappedClasses(rootNode));
			
			module.setConfig(configDoc);
			
			module.setMandatory(getMandatory(rootNode, configVersion));
			
			module.setFile(moduleFile);
			
			module.setConditionalResources(getConditionalResources(rootNode));
		}
		finally {
			try {
				jarfile.close();
			}
			catch (Exception e) {
				log.warn("Unable to close jarfile: " + jarfile, e);
			}
			if (configStream != null) {
				try {
					configStream.close();
				}
				catch (Exception io) {
					log.error("Error while closing config stream for module: " + moduleFile.getAbsolutePath(), io);
				}
			}
		}
		return module;
	}