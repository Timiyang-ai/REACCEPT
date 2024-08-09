public static boolean isCitizenId(String value) {
		return isByRegex(CITIZEN_ID, value);
	}