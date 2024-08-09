public boolean isUserNameValid(String username) {
		//Initialize reg ex for userName pattern 
		String expression = "^[\\w][\\Q_\\E\\w-\\.]{1,49}$";
		
		// empty usernames are allowed
		if (!StringUtils.hasLength((username)))
			return true;
		
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