public static void authenticate(String username, String password) throws ContextAuthenticationException {
		if (log.isDebugEnabled())
			log.debug("Authenticating with username: " + username);
		
		getUserContext().authenticate(username, password, getContextDAO());
	}