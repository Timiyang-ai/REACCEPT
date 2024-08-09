public static boolean areCompatible(Locale lhs, Locale rhs) {
		if (lhs.equals(rhs)) {
			return true;
		} else if (("".equals(lhs.getCountry())) || ("".equals(rhs.getCountry()))) {
			// no country specified, so language match is good enough
			if (lhs.getLanguage().equals(rhs.getLanguage())) {
				return true;
			}
		}
		return false;
	}