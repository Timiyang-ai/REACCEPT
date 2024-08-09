@Authorized({OpenmrsConstants.PRIV_ADD_USERS})
	@Logging(ignoredArgumentIndexes={1})
	public User createUser(User user, String password) throws APIException;