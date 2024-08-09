public static void becomeUser(String username) throws ContextAuthenticationException {
		log.debug("username: " + username);
		getUserContext().becomeUser(username);
	}