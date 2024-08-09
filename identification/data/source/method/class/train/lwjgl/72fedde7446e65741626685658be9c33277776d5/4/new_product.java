private static void initialize() {
		Sys.initialize();

		// Assign names to all the buttons
		buttonName = new String[16];
		for (int i = 0; i < 16; i++) {
			buttonName[i] = "BUTTON" + i;
			buttonMap.put(buttonName[i], i);
		}

		initialized = true;
	}