public static void setDisplayMode(DisplayMode mode) throws LWJGLException {
		if (mode == null)
			throw new NullPointerException("mode must be non-null");
		current_mode = mode;
		if (isCreated()) {
			destroyWindow();
			if (fullscreen)
				switchDisplayMode(current_mode);
			createWindow();
		}
	}