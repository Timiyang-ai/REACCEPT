public Module parse() throws ModuleException {
		
		Module module = null;
		JarFile jarfile = null;
		InputStream configStream = null;
		
		try {
			try {
				jarfile = new JarFile(moduleFile);
			}
			catch (IOException e) {
				throw new ModuleException("Unable to get jar file", moduleFile.getName(), e);
			}
			
			// look for config.xml in the root of the module
			ZipEntry config = jarfile.getEntry("config.xml");
			if (config == null)
				throw new ModuleException("Error loading module. No config.xml found.", moduleFile.getName());
			
			
			// get a config file stream
			try {
				configStream = jarfile.getInputStream(config);
			}
			catch (IOException e) {
				throw new ModuleException("Unable to get config file stream", moduleFile.getName(), e);
			}
			
			
			// turn the config file into an xml document
			Document configDoc = null;
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				configDoc = db.parse(configStream);
			}
			catch (Exception e) {
				throw new ModuleException("Error parsing module config.xml file", moduleFile.getName(), e);
			}
			
			Element rootNode = configDoc.getDocumentElement();
			
			String configVersion = rootNode.getAttribute("configVersion");
			
			if (!validConfigVersions().contains(configVersion))
				throw new ModuleException("Invalid config version: " + configVersion, moduleFile.getName());
			
			String name = getElement(rootNode, configVersion, "name");
			String moduleId = getElement(rootNode, configVersion, "id");
			String packageName = getElement(rootNode, configVersion, "package");
			String author = getElement(rootNode, configVersion, "author");
			String desc = getElement(rootNode, configVersion, "description");
			String version = getElement(rootNode, configVersion, "version");
			
			// do some validation
			if (name == null || name.length() == 0)
				throw new ModuleException("name cannot be empty", moduleFile.getName());
			if (moduleId == null || moduleId.length() == 0)
				throw new ModuleException("module id cannot be empty", name);
			if (packageName == null || packageName.length() == 0)
				throw new ModuleException("package cannot be empty", name);
			
			// create the module object
			module = new Module(name, moduleId, packageName, author, desc, version);
			
			// find and load the activator class
			module.setActivatorName(getElement(rootNode, configVersion, "activator"));
			
			// get libraries
			List<Library> libraries = new Vector<Library>();
			for (ModelLibrary model : getLibraries(rootNode, configVersion))
				libraries.add(new Library(module, model));
			module.setLibraries(libraries);
			
			
			module.setRequireDatabaseVersion(getElement(rootNode, configVersion, "require_database_version"));
			module.setRequireOpenmrsVersion(getElement(rootNode, configVersion, "require_version"));
			module.setUpdateURL(getElement(rootNode, configVersion, "updateURL"));
			module.setRequiredModules(getRequiredModules(rootNode, configVersion));
			
			module.setAdvicePoints(getAdvice(rootNode, configVersion, module));
			module.setExtensionNames(getExtensions(rootNode, configVersion));
			
			module.setPrivileges(getPrivileges(rootNode, configVersion));
			module.setGlobalProperties(getGlobalProperties(rootNode, configVersion));
			
			module.setMessages(getMessages(rootNode, configVersion, jarfile));
			
			module.setMappingFiles(getMappingFiles(rootNode, configVersion, jarfile));
			
			module.setConfig(configDoc);
			
			module.setFile(moduleFile);
		}
		finally {
			try {
				jarfile.close();
			}
			catch (IOException e) {
				log.warn("Unable to close jarfile: " + jarfile.getName());
			}
			if (configStream != null) {
				try {
					configStream.close();					
				}
				catch (IOException io) {
					log.error("Error while closing config stream for module: " + moduleFile.getAbsolutePath(), io);
				}
			}
		}
		
		return module;
	}