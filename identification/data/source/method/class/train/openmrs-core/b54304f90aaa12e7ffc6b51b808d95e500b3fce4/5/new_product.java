public static String sanitizeLocales(String localesString) {
		// quick npe check
		if (localesString == null) {
			return null;
		}
		
		StringBuffer outputString = new StringBuffer();
		
		boolean first = true;
		
		for (String locale : Arrays.asList(localesString.split(","))) {
			Locale loc = normalizeLocale(locale);
			if (loc != null) {
				if (!first) {
					outputString.append(", ");
				} else {
					first = false; // so commas are inserted from now on
				}
				outputString.append(loc.toString());
			}
		}
		if (outputString.length() > 0) {
			return outputString.toString();
		} else {
			return null;
		}
	}