public static void becomeUser(String systemId) throws ContextAuthenticationException {
		if (log.isInfoEnabled()) {
			log.info("systemId: " + systemId);
		}
		
		User user = getUserContext().becomeUser(systemId);
		
		// if assuming identity procedure finished successfully, we should change context locale parameter
		Locale locale = null;
		if (user.getUserProperties().containsKey(OpenmrsConstants.USER_PROPERTY_DEFAULT_LOCALE)) {
			String localeString = user.getUserProperty(OpenmrsConstants.USER_PROPERTY_DEFAULT_LOCALE);
			locale = LocaleUtility.fromSpecification(localeString);
		}
		// when locale parameter is not valid or does not exist
		if (locale == null) {
			locale = LocaleUtility.getDefaultLocale();
		}
		Context.setLocale(locale);
	}