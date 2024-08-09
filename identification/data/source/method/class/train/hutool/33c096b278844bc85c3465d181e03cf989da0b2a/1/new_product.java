public static String cleanBlank(String str) {
		if(str == null) {
			return null;
		}
		
		return str.replaceAll("\\s*", EMPTY);
	}