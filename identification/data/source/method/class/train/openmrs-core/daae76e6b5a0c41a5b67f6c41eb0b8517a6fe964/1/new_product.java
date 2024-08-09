public Module parse(File moduleFile) {
		validateFileIsNotNull(moduleFile);
		validateFileHasModuleFileExtension(moduleFile);
		return createModule(getModuleConfigXml(moduleFile), moduleFile);
	}