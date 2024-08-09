	protected ContextSession getContextSession(MySession session,
			String contextId) {
		allowCreateUuidRequest();
		return session.getContextSession(contextId);
	}