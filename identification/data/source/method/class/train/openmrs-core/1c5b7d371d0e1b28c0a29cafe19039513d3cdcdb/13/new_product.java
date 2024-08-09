public static void authenticate(String username, String password) throws ContextAuthenticationException {
		if (log.isDebugEnabled())
			log.debug("username: " + username);
		
		getUserContext().authenticate(username, password);
	}