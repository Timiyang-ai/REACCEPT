public static void logout() {
		log.debug("Logging out : " + getAuthenticatedUser());
		getUserContext().logout();
		clearUserContext();
	}