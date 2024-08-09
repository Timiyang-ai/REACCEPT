public static void becomeUser(String systemId) throws ContextAuthenticationException {
		if (log.isInfoEnabled())
			log.info("systemId: " + systemId);
		
		getUserContext().becomeUser(systemId);
	}