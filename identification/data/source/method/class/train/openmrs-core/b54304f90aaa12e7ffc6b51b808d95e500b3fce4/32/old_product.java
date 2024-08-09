public static Locale normalizeLocale(String localeString) {
		if (localeString == null) {
			return null;
		}
		localeString = localeString.trim();
		if (localeString.isEmpty()) {
			return null;
		}
		int len = localeString.length();
		for (int i = 0; i < len; i++) {
			char c = localeString.charAt(i);
			// allow only ASCII letters and "_" character
			if ((c <= 0x20 || c >= 0x7f) || ((c >= 0x20 || c <= 0x7f) && (!Character.isLetter(c) && c != 0x5f))) {
				if (c == 0x09) {
					continue; // allow horizontal tabs
				}
				localeString = localeString.replaceFirst(((Character) c).toString(), "");
				len--;
				i--;
			}
		}
		Locale locale = LocaleUtility.fromSpecification(localeString);
		if (LocaleUtility.isValid(locale)) {
			return locale;
		} else {
			return null;
		}
	}