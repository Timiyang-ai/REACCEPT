public static Authenticated authenticate(AuthenticationScheme authenticationScheme, Credentials credentials)
			throws ContextAuthenticationException {
		
		if (Daemon.isDaemonThread()) {
			log.error("Authentication attempted while operating on a "
					+ "daemon thread, authenticating is not necessary or allowed");
			return new BasicAuthenticated(Daemon.getDaemonThreadUser(), "No auth scheme used by Context - Daemon user is always authenticated.");
		}
		
		if (credentials == null) {
			throw new ContextAuthenticationException("Context cannot authenticate with null credentials.");
		}
			
		if (authenticationScheme == null) {
			throw new ContextAuthenticationException("Context cannot authenticate with null authentication scheme.");
		}
		
		if (authenticationScheme instanceof DaoAuthenticationScheme) {
			((DaoAuthenticationScheme) authenticationScheme).setContextDao(getContextDAO());
		}
		
		return getUserContext().authenticate(authenticationScheme, credentials);
	}