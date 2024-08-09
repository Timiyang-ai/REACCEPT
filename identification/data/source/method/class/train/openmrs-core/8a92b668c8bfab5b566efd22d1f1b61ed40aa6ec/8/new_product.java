public static Module loadModule(File moduleFile) throws ModuleException {
		
		Module module = getModuleFromFile(moduleFile);
		
		if (module != null)
			loadModule(module);
		
		return module;
		
	}