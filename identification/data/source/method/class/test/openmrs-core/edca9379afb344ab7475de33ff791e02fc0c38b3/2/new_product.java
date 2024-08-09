public static void refreshAuthenticatedUser() {
		if (Daemon.isDaemonThread()) {
			return;
		}
		log.debug("Refreshing authenticated user");

		getUserContext().refreshAuthenticatedUser();
	}