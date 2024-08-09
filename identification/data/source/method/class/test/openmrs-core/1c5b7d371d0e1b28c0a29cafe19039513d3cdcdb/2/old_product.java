public static void logout() {
		log.info("Logging out : " + getAuthenticatedUser());
		getUserContext().logout();
		clearUserContext();
	}