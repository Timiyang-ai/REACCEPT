public Module parse(File moduleFile) {
		if (moduleFile == null) {
			throw new ModuleException(messageSourceService.getMessage("Module.error.fileCannotBeNull"));
		}
		if (!moduleFile.getName().endsWith(".omod")) {
			throw new ModuleException(messageSourceService.getMessage("Module.error.invalidFileExtension"),
				moduleFile.getName());
		}
		return createModule(getModuleConfigXml(moduleFile), moduleFile);
	}