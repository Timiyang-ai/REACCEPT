public Locale getLocale() {
		if (locale == null)
			locale = LocaleUtility.getDefaultLocale();
		
		return locale;
	}