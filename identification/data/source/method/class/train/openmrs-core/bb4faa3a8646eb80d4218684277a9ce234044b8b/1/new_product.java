@Deprecated
	public static void authenticate(String username, String password) throws ContextAuthenticationException {
		authenticate(new UsernamePasswordCredentials(username, password));
	}