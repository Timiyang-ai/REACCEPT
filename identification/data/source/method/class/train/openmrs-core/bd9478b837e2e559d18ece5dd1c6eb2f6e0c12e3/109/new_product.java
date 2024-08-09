@Authorized({OpenmrsConstants.PRIV_EDIT_USERS})
	@Logging(ignoredArgumentIndexes={1})
	public void changePassword(User u, String pw) throws APIException;