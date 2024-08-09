public Module parse() throws ModuleException {
		
		Module module = null;
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
			if (config == null)
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.noConfigFile"),
				        moduleFile.getName());
			
			// get a config file stream
			try {
				configStream = jarfile.getInputStream(config);
			}
			catch (IOException e) {
				throw new ModuleException(Context.getMessageSourceService().getMessage(
				    "Module.error.cannotGetConfigFileStream"), moduleFile.getName(), e);
			}
			
			// turn the config file into an xml document
			Document configDoc = null;
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				db.setEntityResolver(new EntityResolver() {
					
					public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
						// When asked to resolve external entities (such as a
						// DTD) we return an InputSource
						// with no data at the end, causing the parser to ignore
						// the DTD.
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
					while ((bytes_read = configStream.read(buffer)) != -1)
						out.write(buffer, 0, bytes_read);
					output = out.toString();
				}
				catch (Exception e2) {
					log.warn("Another error parsing config.xml", e2);
				}
				finally {
					try {
						out.close();
					}
					catch (Exception e3) {}
				}
				
				log.error("config.xml content: " + output);
				throw new ModuleException(
				        Context.getMessageSourceService().getMessage("Module.error.cannotParseConfigFile"), moduleFile
				                .getName(), e);
			}
			
			Element rootNode = configDoc.getDocumentElement();
			
			String configVersion = rootNode.getAttribute("configVersion").trim();
			
			if (!validConfigVersions.contains(configVersion))
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.invalidConfigVersion",
				    new Object[] { configVersion }, Context.getLocale()), moduleFile.getName());
			
			String name = getElement(rootNode, configVersion, "name").trim();
			String moduleId = getElement(rootNode, configVersion, "id").trim();
			String packageName = getElement(rootNode, configVersion, "package").trim();
			String author = getElement(rootNode, configVersion, "author").trim();
			String desc = getElement(rootNode, configVersion, "description").trim();
			String version = getElement(rootNode, configVersion, "version").trim();
			
			// do some validation
			if (name == null || name.length() == 0)
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.nameCannotBeEmpty"),
				        moduleFile.getName());
			if (moduleId == null || moduleId.length() == 0)
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.idCannotBeEmpty"), name);
			if (packageName == null || packageName.length() == 0)
				throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.packageCannotBeEmpty"),
				        name);
			
			// look for log4j.xml in the root of the module
			Document log4jDoc = null;
			try {
				ZipEntry log4j = jarfile.getEntry("log4j.xml");
				if (log4j != null) {
					InputStream log4jStream = jarfile.getInputStream(log4j);
					
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					db.setEntityResolver(new EntityResolver() {
						
						public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
							// When asked to resolve external entities (such as
							// a
							// DTD) we return an InputSource
							// with no data at the end, causing the parser to
							// ignore
							// the DTD.
							return new InputSource(new StringReader(""));
						}
					});
					
					log4jDoc = db.parse(log4jStream);
				}
			}
			catch (Exception e) {}
			
			// create the module object
			module = new Module(name, moduleId, packageName, author, desc, version);
			
			// find and load the activator class
			module.setActivatorName(getElement(rootNode, configVersion, "activator").trim());
			
			module.setRequireDatabaseVersion(getElement(rootNode, configVersion, "require_database_version").trim());
			module.setRequireOpenmrsVersion(getElement(rootNode, configVersion, "require_version").trim());
			module.setUpdateURL(getElement(rootNode, configVersion, "updateURL").trim());
			module.setRequiredModulesMap(getRequiredModules(rootNode, configVersion));
			
			module.setAdvicePoints(getAdvice(rootNode, configVersion, module));
			module.setExtensionNames(getExtensions(rootNode, configVersion));
			
			module.setPrivileges(getPrivileges(rootNode, configVersion));
			module.setGlobalProperties(getGlobalProperties(rootNode, configVersion));
			
			module.setMessages(getMessages(rootNode, configVersion, jarfile));
			
			module.setMappingFiles(getMappingFiles(rootNode, configVersion, jarfile));
			
			module.setConfig(configDoc);
			
			module.setLog4j(log4jDoc);
			
			module.setMandatory(getMandatory(rootNode, configVersion, jarfile));
			
			module.setFile(moduleFile);
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