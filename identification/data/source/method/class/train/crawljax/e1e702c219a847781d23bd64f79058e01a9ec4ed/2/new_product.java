public static String getVarFromQueryString(String varName, String haystack) {
		if (haystack == null || haystack.length() == 0) {
			return null;
		}
		
		String modifiedHaystack = haystack;
		
		if (modifiedHaystack.charAt(0) == '?') {
			modifiedHaystack = modifiedHaystack.substring(1);
		}
		String[] vars = modifiedHaystack.split("&");

		for (String var : vars) {
			String[] tuple = var.split("=");
			if (tuple.length == 2 && tuple[0].equals(varName)) {
				return tuple[1];
			}
		}
		return null;
	}