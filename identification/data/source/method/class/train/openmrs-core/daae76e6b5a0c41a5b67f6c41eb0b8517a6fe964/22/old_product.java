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
			module = createModule(configDoc);
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