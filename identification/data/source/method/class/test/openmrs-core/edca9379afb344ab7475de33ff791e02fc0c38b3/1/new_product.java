public static void refreshAuthenticatedUser() {
		if (Daemon.isDaemonThread()) {
			return;
		}
		
		if (log.isDebugEnabled()) {
			log.debug("Refreshing authenticated user");
		}
		
		getUserContext().refreshAuthenticatedUser();
	}