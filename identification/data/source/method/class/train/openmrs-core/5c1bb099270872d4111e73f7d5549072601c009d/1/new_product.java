public static void validatePassword(String username, String password, String systemId) throws PasswordException {

		AdministrationService svc = Context.getAdministrationService();
		
		if (password == null) {
			throw new WeakPasswordException();
		}
		
		String userGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_CANNOT_MATCH_USERNAME_OR_SYSTEMID, "true");
		if ("true".equals(userGp) && (password.equals(username) || password.equals(systemId))) {
			throw new WeakPasswordException();
		}
		
		String lengthGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_MINIMUM_LENGTH, "8");
		if (StringUtils.isNotEmpty(lengthGp)) {
			try {
				int minLength = Integer.parseInt(lengthGp);
				if (password.length() < minLength) {
					throw new ShortPasswordException(getMessage("error.password.length", lengthGp));
				}
			}
			catch (NumberFormatException nfe) {
				log.warn("Error in global property <" + OpenmrsConstants.GP_PASSWORD_MINIMUM_LENGTH + "> must be an Integer");
			}
		}
		
		String caseGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_REQUIRES_UPPER_AND_LOWER_CASE, "true");
		if ("true".equals(caseGp) && !containsUpperAndLowerCase(password)) {
			throw new InvalidCharactersPasswordException(getMessage("error.password.requireMixedCase"));
		}
		
		String digitGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_REQUIRES_DIGIT, "true");
		if ("true".equals(digitGp) && !containsDigit(password)) {
			throw new InvalidCharactersPasswordException(getMessage("error.password.requireNumber"));
		}
		
		String nonDigitGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_REQUIRES_NON_DIGIT, "true");
		if ("true".equals(nonDigitGp) && containsOnlyDigits(password)) {
			throw new InvalidCharactersPasswordException(getMessage("error.password.requireLetter"));
		}

		String regexGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_CUSTOM_REGEX);
		if (StringUtils.isNotEmpty(regexGp)) {
			try {
				Pattern pattern = Pattern.compile(regexGp);
				Matcher matcher = pattern.matcher(password);
				if (!matcher.matches()) {
					throw new InvalidCharactersPasswordException(getMessage("error.password.different"));
				}
			}
			catch (PatternSyntaxException pse) {
				log.warn("Invalid regex of " + regexGp + " defined in global property <" + 
						  OpenmrsConstants.GP_PASSWORD_CUSTOM_REGEX + ">.");
			}
		}
	}