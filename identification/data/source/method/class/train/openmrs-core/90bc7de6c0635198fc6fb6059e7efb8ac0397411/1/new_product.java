public boolean skipFilter(HttpServletRequest request) {
		return !Listener.errorOccurredAtStartup();
	}