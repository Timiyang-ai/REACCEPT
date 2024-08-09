@Transactional(readOnly=true)
	boolean isSecretAnswer(User u, String answer);