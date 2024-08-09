private static void initialize() {
		if (initialized)
			return;
		Sys.initialize();
		initIDs();
		initialized = true;
	}