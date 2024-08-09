@Transactional(noRollbackFor = ContextAuthenticationException.class)
	public User authenticate(String username, String password) throws ContextAuthenticationException;