@Authorized( { OpenmrsConstants.PRIV_EDIT_USER_PASSWORDS })
	@Logging(ignoredArgumentIndexes = { 1 })
	public void changePassword(User u, String pw) throws APIException;