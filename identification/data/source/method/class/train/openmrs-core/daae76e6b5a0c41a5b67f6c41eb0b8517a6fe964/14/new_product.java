public Module parse() throws ModuleException {
		
		Document configDoc;

		try (JarFile jarfile = new JarFile(moduleFile)) {
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
		catch (IOException e) {
			throw new ModuleException(Context.getMessageSourceService().getMessage("Module.error.cannotGetJarFile"),
				moduleFile.getName(), e);
		}
		return createModule(configDoc);
	}