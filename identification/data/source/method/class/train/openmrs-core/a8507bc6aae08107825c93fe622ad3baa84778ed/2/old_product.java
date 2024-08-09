public static boolean areCompatible(Locale lhs, Locale rhs) {
		if (lhs.equals(rhs)) {
			return true;
		} else if ((("".equals(lhs.getCountry())) || ("".equals(rhs.getCountry())))
		        && lhs.getLanguage().equals(rhs.getLanguage())) {
			// no country specified, so language match is good enough
			return true;
		}
		return false;
	}