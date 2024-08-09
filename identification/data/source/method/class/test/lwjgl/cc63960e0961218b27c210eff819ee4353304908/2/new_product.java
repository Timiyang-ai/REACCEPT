public static void setDisplayMode(DisplayMode mode) throws LWJGLException {
		if (mode == null)
			throw new NullPointerException("mode must be non-null");
		if (isCreated()) {
			destroyWindow();
			if (fullscreen)
				switchDisplayMode(current_mode);
			try {
				createWindow();
			} catch (LWJGLException e) {
				reset();
				throw e;
			}
		}
		current_mode = mode;
	}