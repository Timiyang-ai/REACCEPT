public boolean isSecretAnswer(User u, String answer) {
		return getUserDAO().isSecretAnswer(u, answer);
	}