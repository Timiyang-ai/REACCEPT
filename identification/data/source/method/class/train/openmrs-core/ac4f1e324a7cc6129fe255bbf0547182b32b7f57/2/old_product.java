@Transactional(readOnly=true)
	public User authenticate(String username, String password)
		throws ContextAuthenticationException;