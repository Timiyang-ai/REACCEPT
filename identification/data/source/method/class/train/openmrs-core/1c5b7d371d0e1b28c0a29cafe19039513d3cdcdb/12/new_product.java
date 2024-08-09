public void authenticate(String username, String password)
			throws ContextAuthenticationException {
		getDAOContext().authenticate(username, password);
	}