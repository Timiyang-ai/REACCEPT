public void authenticate(String username, String password)
			throws ContextAuthenticationException {
		getDaoContext().authenticate(username, password);
		user = getDaoContext().getAuthenticatedUser();
	}