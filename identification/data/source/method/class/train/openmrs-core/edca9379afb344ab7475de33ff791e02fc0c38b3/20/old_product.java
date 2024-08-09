public static void refreshAuthenticatedUser() {
		if (log.isDebugEnabled())
			log.debug("Refreshing authenticated user");
		
		getUserContext().refreshAuthenticatedUser();
	}