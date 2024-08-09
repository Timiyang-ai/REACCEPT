public static boolean nullSafeEqualsIgnoreCase(String s1, String s2) {
		if (s1 == null) {
			return s2 == null;
		} else if (s2 == null) {
			return false;
		}
		
		return s1.equalsIgnoreCase(s2);
	}