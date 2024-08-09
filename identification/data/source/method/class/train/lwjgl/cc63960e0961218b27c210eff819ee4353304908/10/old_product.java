public static void setDisplayMode(DisplayMode mode) throws LWJGLException {
		synchronized ( GlobalLock.lock ) {
			if ( mode == null )
				throw new NullPointerException("mode must be non-null");
			current_mode = mode;
			if ( isCreated() ) {
				destroyWindow();
				// If mode is not fullscreen capable, make sure we are in windowed mode
				if ( !mode.isFullscreen() )
					resetFullscreen();
				try {
					if ( fullscreen )
						switchDisplayMode();
					createWindow();
					makeCurrentAndSetSwapInterval();
				} catch (LWJGLException e) {
					destroyContext();
					destroyPeerInfo();
					display_impl.resetDisplayMode();
					throw e;
				}
			}
		}
	}