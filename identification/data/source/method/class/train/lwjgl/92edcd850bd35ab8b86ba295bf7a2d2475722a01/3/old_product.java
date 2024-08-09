public void init() {
		
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
		
		// get colors of applet
		bgColor 		= getColor("al_bgcolor", Color.white);
		fgColor 		= getColor("al_fgcolor", Color.black);
		errorColor 		= getColor("al_errorcolor", Color.red);		

		// load logos
		logo 			= getImage("/" + getParameter("al_logo"));
		progressbar 	= getImage("/" + getParameter("al_progressbar"));
		
		// jars to load
		jarList 		= getParameter("al_jars");
		
		//sanity check
		if(logo == null || progressbar == null) {
			fatalErrorOccured("Unable to load logo and progressbar images");
			return;
		}

		// parse the urls for the jars into the url list
		loadJarURLs();
	}