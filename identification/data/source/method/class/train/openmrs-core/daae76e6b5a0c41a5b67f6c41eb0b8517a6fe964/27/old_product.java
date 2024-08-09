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
				db.setEntityResolver(new EntityResolver(){
					public InputSource resolveEntity(String publicId, String systemId) 
							throws SAXException, IOException {
						// When asked to resolve external entities (such as a DTD) we return an InputSource
						// with no data at the end, causing the parser to ignore the DTD.
						return new InputSource(new StringReader(""));
					}
				});

				configDoc = db.parse(configStream);
			}
			catch (Exception e) {
				log.error("Error parsing config.xml: " + configStream.toString(), e);
				
				OutputStream out = null;
				String output = "";
				try {
					out = new ByteArrayOutputStream();
			        // Now copy bytes from the URL to the output stream
			        byte[] buffer = new byte[4096];
			        int bytes_read;
			        while((bytes_read = configStream.read(buffer)) != -1)
			            out.write(buffer, 0, bytes_read);
			        output = out.toString();
				}
				catch (Exception e2) {
					log.warn("Another error parsing config.xml", e2);
				}
				finally {
					try { out.close(); } catch (Exception e3) {};
				}
		        
				log.error("config.xml content: " + output);
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
			catch (Exception e) {
				log.warn("Unable to close jarfile: " + jarfile.getName());
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