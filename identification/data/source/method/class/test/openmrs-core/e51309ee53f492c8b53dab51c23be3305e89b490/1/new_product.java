public static void validatePassword(String username, String password, String systemId) throws PasswordException {
		
		// default values for all of the global properties
		String userGp = "true";
		String lengthGp = "8";
		String caseGp = "true";
		String digitGp = "true";
		String nonDigitGp = "true";
		String regexGp = null;
		AdministrationService svc = null;
		
		try {
			svc = Context.getAdministrationService();
		}
		catch (APIException apiEx) {
			// if a service isn't available, fail quietly and just do the defaults
			log.debug("Unable to get global properties", apiEx);
		}
		
		if (svc != null) {
			userGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_CANNOT_MATCH_USERNAME_OR_SYSTEMID, userGp);
			lengthGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_MINIMUM_LENGTH, lengthGp);
			caseGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_REQUIRES_UPPER_AND_LOWER_CASE, caseGp);
			digitGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_REQUIRES_DIGIT, digitGp);
			nonDigitGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_REQUIRES_NON_DIGIT, nonDigitGp);
			regexGp = svc.getGlobalProperty(OpenmrsConstants.GP_PASSWORD_CUSTOM_REGEX, regexGp);
		}
		
		if (password == null) {
			throw new WeakPasswordException();
		}
		
		if ("true".equals(userGp) && (password.equals(username) || password.equals(systemId))) {
			throw new WeakPasswordException();
		}
		
		if (StringUtils.isNotEmpty(lengthGp)) {
			try {
				int minLength = Integer.parseInt(lengthGp);
				if (password.length() < minLength) {
					throw new ShortPasswordException(getMessage("error.password.length", lengthGp));
				}
			}
			catch (NumberFormatException nfe) {
				log
				        .warn("Error in global property <" + OpenmrsConstants.GP_PASSWORD_MINIMUM_LENGTH
				                + "> must be an Integer");
			}
		}
		
		if ("true".equals(caseGp) && !containsUpperAndLowerCase(password)) {
			throw new InvalidCharactersPasswordException(getMessage("error.password.requireMixedCase"));
		}
		
		if ("true".equals(digitGp) && !containsDigit(password)) {
			throw new InvalidCharactersPasswordException(getMessage("error.password.requireNumber"));
		}
		
		if ("true".equals(nonDigitGp) && containsOnlyDigits(password)) {
			throw new InvalidCharactersPasswordException(getMessage("error.password.requireLetter"));
		}
		
		if (StringUtils.isNotEmpty(regexGp)) {
			try {
				Pattern pattern = Pattern.compile(regexGp);
				Matcher matcher = pattern.matcher(password);
				if (!matcher.matches()) {
					throw new InvalidCharactersPasswordException(getMessage("error.password.different"));
				}
			}
			catch (PatternSyntaxException pse) {
				log.warn("Invalid regex of " + regexGp + " defined in global property <"
				        + OpenmrsConstants.GP_PASSWORD_CUSTOM_REGEX + ">.");
			}
		}
	}