public static Module loadModule(File moduleFile) {
		
		Module module = getModuleFromFile(moduleFile);
		
		if (module != null)
			loadModule(module);
		
		return module;
		
	}