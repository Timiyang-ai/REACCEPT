@Authorized( { PrivilegeConstants.EDIT_USER_PASSWORDS })
	@Logging(ignoredArgumentIndexes = { 1 })
	@Deprecated
	public void changePassword(User u, String pw) throws APIException;