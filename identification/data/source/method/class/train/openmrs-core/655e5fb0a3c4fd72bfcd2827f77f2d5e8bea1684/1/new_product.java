public static void becomeUser(String systemId) throws ContextAuthenticationException {
		if (log.isDebugEnabled())
			log.debug("systemId: " + systemId);
		
		getUserContext().becomeUser(systemId);
	}