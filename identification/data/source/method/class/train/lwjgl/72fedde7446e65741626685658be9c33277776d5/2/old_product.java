private static void initialize() {
		System.loadLibrary(Sys.getLibraryName());
		initIDs();
		initialized = true;
	}