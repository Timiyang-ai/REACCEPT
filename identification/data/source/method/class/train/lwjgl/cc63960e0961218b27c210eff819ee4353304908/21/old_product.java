public static void setDisplayMode(DisplayMode mode) throws LWJGLException {
		if (mode == null)
			throw new NullPointerException("mode must be non-null");
		current_mode = mode;
		if (isCreated()) {
			destroyWindow();
			setFullscreen(mode.isFullscreen());
			try {
				if (fullscreen)
					switchDisplayMode();
				createWindow();
			} catch (LWJGLException e) {
				display_impl.destroyContext();
				display_impl.resetDisplayMode();
				throw e;
			}
		}
	}