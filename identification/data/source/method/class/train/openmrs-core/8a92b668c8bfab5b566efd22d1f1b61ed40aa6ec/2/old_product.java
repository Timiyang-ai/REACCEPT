public static Module loadModule(File moduleFile, Boolean replaceIfExists) throws ModuleException {
		Module module = new ModuleFileParser(moduleFile).parse();
		
		if (module != null) {
			loadModule(module, replaceIfExists);
		}
		
		return module;
	}