	protected ToolSession getToolSession(MySession session,
			String placementId) {
		allowCreateUuidRequest();
		return session.getToolSession(placementId);
	}