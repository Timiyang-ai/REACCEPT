public static void authenticate(String username, String password) throws ContextAuthenticationException {
		if (log.isDebugEnabled()) {
			log.debug("Authenticating with username: " + username);
		}

		if (Daemon.isDaemonThread()) {
			log.error("Authentication attempted while operating on a "
					+ "daemon thread, authenticating is not necessary or allowed");
			return;
		}

		getUserContext().authenticate(username, password, getContextDAO());
	}