public static boolean areCompatible(Locale lhs, Locale rhs) {
		return lhs.equals(rhs) || (("".equals(lhs.getCountry())) || ("".equals(rhs.getCountry()))) && lhs.getLanguage()
				.equals(rhs.getLanguage());
	}