public static void setDisplayMode(DisplayMode mode) throws LWJGLException {
		if (mode == null)
			throw new NullPointerException("mode must be non-null");
		current_mode = mode;
		if (isCreated()) {
			destroyWindow();
			try {
				if (fullscreen)
					switchDisplayMode(mode);
				createWindow();
			} catch (LWJGLException e) {
				destroyContext();
				reset();
				throw e;
			}
		}
	}