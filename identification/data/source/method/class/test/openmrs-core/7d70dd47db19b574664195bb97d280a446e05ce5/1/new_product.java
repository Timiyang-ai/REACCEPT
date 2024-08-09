public boolean isUserNameValid(String username) {
		//Initialize reg ex for userName pattern
		// ^ = start of line
		// \w = [a-zA-Z_0-9]
		// \Q = quote everything until \E 
		// $ = end of line
		// complete meaning = 2-50 characters, the first must be a letter, digit, or _, and the rest may also be - or .
		String expression = "^[\\w][\\Q_\\E\\w-\\.]{1,49}$";
		// empty usernames are allowed
		if (StringUtils.isEmpty(username)) {
			return true;
		}
		
		try {
			//Make the comparison case-insensitive.
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(username);
			return matcher.matches();
		}
		catch (PatternSyntaxException pex) {
			log.error("Username Pattern Syntax exception in UserValidator", pex);
			return false;
		}
	}