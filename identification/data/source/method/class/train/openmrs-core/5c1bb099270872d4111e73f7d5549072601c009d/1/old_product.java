public static void validatePassword(String username, String password, String systemId) throws PasswordException {
		if (password.length() < 8)
			throw new ShortPasswordException();
		Pattern pattern = Pattern.compile("^(?=.*?[0-9])(?=.*?[A-Z])[\\w|\\W]*$");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches())
			throw new InvalidCharactersPasswordException();
		if (password.equals(username) || password.equals(systemId))
			throw new WeakPasswordException();
	}