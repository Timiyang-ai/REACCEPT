public static String htmlspecialchars(String input) {
		if (input == null)
			return null;
		String retval = input.replace("&", "&amp;");
		retval = retval.replace("\"", "&quot;");
		retval = retval.replace("<", "&lt;");
		retval = retval.replace(">", "&gt;");
		retval = retval.replace("=", "&#61;");
		return retval;
	}