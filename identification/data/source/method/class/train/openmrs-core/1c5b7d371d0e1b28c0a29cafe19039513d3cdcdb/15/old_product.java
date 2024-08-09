public static void logout() {
		if (log.isDebugEnabled())
			log.debug("Logging out : " + getAuthenticatedUser());
		
		getUserContext().logout();
		clearUserContext();
	}