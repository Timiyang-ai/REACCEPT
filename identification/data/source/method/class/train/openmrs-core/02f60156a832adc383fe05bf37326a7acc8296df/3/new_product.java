public static Locale getLocale() {
		// if a session hasn't been opened, just fetch the default
		if (!isSessionOpen()) {
			return LocaleUtility.getDefaultLocale();
		}
		
		return getUserContext().getLocale();
	}