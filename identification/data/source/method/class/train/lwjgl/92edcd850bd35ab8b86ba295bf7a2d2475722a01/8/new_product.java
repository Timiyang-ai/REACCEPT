public void init() {
		state = STATE_INIT;
		
		// sanity check
		String[] requiredArgs = {"al_main", "al_logo", "al_progressbar", "al_jars"};
		for(int i=0; i<requiredArgs.length; i++) {
			if(getParameter(requiredArgs[i]) == null) {
				fatalErrorOccured("missing required applet parameter: " + requiredArgs[i]);
				return;				
			}
		}

		// whether to run in debug mode
		debugMode 		= getBooleanParameter("al_debug", false);
		
		// whether to prepend host to cache path
		prependHost 	= getBooleanParameter("al_prepend_host", true);
		
		// get colors of applet
		bgColor 		= getColor("al_bgcolor", Color.white);
		setBackground(bgColor);

		fgColor 		= getColor("al_fgcolor", Color.black);
		errorColor 		= getColor("al_errorcolor", Color.red);		

		// load logos
		logo 			= getImage("/" + getParameter("al_logo"));
		progressbar 	= getImage("/" + getParameter("al_progressbar"));
		
		//sanity check
		if(logo == null || progressbar == null) {
			fatalErrorOccured("Unable to load logo and progressbar images");
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