public Module parse() throws ModuleException {
		
		JarFile jarfile = null;
		Document configDoc;

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

			try (InputStream configStream = jarfile.getInputStream(config)) {
				configDoc = parseConfig(configStream);
			}
			catch (IOException e) {
				throw new ModuleException(Context.getMessageSourceService().getMessage(
				    "Module.error.cannotGetConfigFileStream"), moduleFile.getName(), e);
			}
		}
		finally {
			try {
				jarfile.close();
			}
			catch (Exception e) {
				log.warn("Unable to close jarfile: " + jarfile, e);
			}
		}
		return createModule(configDoc);
	}