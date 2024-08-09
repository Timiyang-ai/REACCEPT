@Transactional(readOnly=true)
	@Logging(ignoredArgumentIndexes={1})
	public boolean isSecretAnswer(User u, String answer) throws APIException;