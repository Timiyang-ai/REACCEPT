public static void authenticate(String username, String password) throws ContextAuthenticationException {
		log.debug("username: " + username);
		getUserContext().authenticate(username, password);
	}