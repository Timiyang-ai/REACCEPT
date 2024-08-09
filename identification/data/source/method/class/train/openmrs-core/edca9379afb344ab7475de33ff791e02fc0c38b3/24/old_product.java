public static void refreshAuthenticatedUser() {
		if (Daemon.isDaemonUser())
			return;
		
		if (log.isDebugEnabled())
			log.debug("Refreshing authenticated user");
		
		getUserContext().refreshAuthenticatedUser();
	}