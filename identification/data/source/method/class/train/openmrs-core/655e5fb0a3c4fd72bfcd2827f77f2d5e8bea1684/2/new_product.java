public static void becomeUser(String systemId) throws ContextAuthenticationException {
		log.debug("systemId: " + systemId);
		getUserContext().becomeUser(systemId);
	}