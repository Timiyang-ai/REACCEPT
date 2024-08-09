public Map<Locale, String> getValueAsString() {
		Map<Locale, String> ret = new HashMap<Locale, String>();
		Locale[] locales = Locale.getAvailableLocales();
		for (int i=0; i<locales.length; i++) {
			ret.put(locales[i], getValueAsString(locales[i]));
		}
		return ret;
	}