private static void initialize() {
		System.loadLibrary(Sys.getLibraryName());
		initIDs();
		
		// Assign names to all the buttons
		buttonName = new String[16];
		for (int i = 0; i < 16; i ++) {
			buttonName[i] = "BUTTON" + i;
			buttonMap.put(buttonName[i], new Integer(i));
		}
	}