@Authorized( { PrivilegeConstants.ADD_USERS, PrivilegeConstants.EDIT_USERS })
	@Logging(ignoredArgumentIndexes = { 1 })
	public User saveUser(User user, String password) throws APIException;