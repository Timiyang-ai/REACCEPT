public static Module loadModule(File moduleFile, Boolean replaceIfExists) throws ModuleException {
		Module module = getModuleFromFile(moduleFile);
		
		if (module != null) {
			loadModule(module, replaceIfExists);
		}
		
		return module;
	}