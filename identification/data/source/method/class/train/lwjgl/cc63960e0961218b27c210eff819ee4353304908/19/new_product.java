public static void setDisplayMode(DisplayMode mode) throws LWJGLException {
		synchronized ( GlobalLock.lock ) {
			if ( mode == null )
				throw new NullPointerException("mode must be non-null");
			boolean was_fullscreen = isFullscreen();
			current_mode = mode;
			if ( !isCreated() || parent != null )
				return;
			destroyWindow();
			// If mode is not fullscreen capable, make sure we are in windowed mode
			try {
				if ( was_fullscreen && !isFullscreen() )
					display_impl.resetDisplayMode();
                else if ( isFullscreen() )
					switchDisplayMode();
				createWindow();
				makeCurrentAndSetSwapInterval();
			} catch (LWJGLException e) {
				drawable.destroy();
				display_impl.resetDisplayMode();
				throw e;
			}
		}
	}