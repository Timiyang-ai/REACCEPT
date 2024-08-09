@Authorized( { OpenmrsConstants.PRIV_EDIT_USER_PASSWORDS })
	@Logging(ignoredArgumentIndexes = { 1, 2 })
	public void changeQuestionAnswer(User u, String question, String answer) throws APIException;