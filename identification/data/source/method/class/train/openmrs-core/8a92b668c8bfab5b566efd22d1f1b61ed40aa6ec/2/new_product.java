public static Module loadModule(File moduleFile, Boolean replaceIfExists) throws ModuleException {
		Module module = new ModuleFileParser(Context.getMessageSourceService()).parse(moduleFile);
		
		if (module != null) {
			loadModule(module, replaceIfExists);
		}
		
		return module;
	}