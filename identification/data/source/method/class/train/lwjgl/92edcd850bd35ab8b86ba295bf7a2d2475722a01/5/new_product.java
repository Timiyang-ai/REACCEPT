public void init() {
		setState(STATE_INIT);
		
		// sanity check
		String[] requiredArgs = {"al_main", "al_jars"};
		for ( String requiredArg : requiredArgs ) {
			if ( getParameter(requiredArg) == null ) {
				fatalErrorOccured("missing required applet parameter: " + requiredArg, null);
				return;
			}
		}
		
		// whether to use cache system
		cacheEnabled	= getBooleanParameter("al_cache", true);

		// whether to run in debug mode
		debugMode 		= getBooleanParameter("al_debug", false);

		// whether to prepend host to cache path
		prependHost 	= getBooleanParameter("al_prepend_host", true);

		// whether to run in headless mode
		headless		= getBooleanParameter("al_headless", false);
		
		// obtain the number of concurrent lookup threads to use
		concurrentLookupThreads = getIntParameter("al_lookup_threads", 1); // defaults to 1
		
		// get colors of applet
		bgColor 		= getColor("boxbgcolor", Color.white);
		setBackground(bgColor);
		fgColor 		= getColor("boxfgcolor", Color.black);

		if (!headless) {
			// load logos
			logo 		= getImage(getStringParameter("al_logo", "appletlogo.gif"));
			progressbar = getImage(getStringParameter("al_progressbar", "appletprogress.gif"));
		}

		// check for lzma support
		try {
			Class.forName("LZMA.LzmaInputStream");
			lzmaSupported = true;
		} catch (Throwable e) {
			/* no lzma support */
		}

		// check pack200 support
		try {
			java.util.jar.Pack200.class.getSimpleName();
			pack200Supported = true;
		} catch (Throwable e) {
			/* no pack200 support */
		}
	}