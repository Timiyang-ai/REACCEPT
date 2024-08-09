public static void setDisplayMode(DisplayMode mode) throws LWJGLException {
		current_mode = mode;
		if (isCreated()) {
			destroyWindow();
			if (fullscreen)
				switchDisplayMode(current_mode);
			createWindow();
		}
	}