public static void create(
		DisplayMode displayMode,
		boolean fullscreen)
		throws Exception {

		if (created)
			return;

		if (!nCreate(displayMode.width,
			displayMode.height,
			displayMode.bpp,
			displayMode.freq,
			fullscreen))
			throw new Exception("Failed to set display mode to " + displayMode);

		created = true;
		mode = displayMode;
	}