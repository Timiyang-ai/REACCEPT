public static String shortenedStackTrace(String stackTrace) {
		if (stackTrace == null) {
			return null;
		}
		
		List<String> results = new ArrayList<>();
		final Pattern exclude = Pattern.compile("(org.springframework.|java.lang.reflect.Method.invoke|sun.reflect.)");
		boolean found = false;
		
		for (String line : stackTrace.split("\n")) {
			Matcher m = exclude.matcher(line);
			if (m.find()) {
				found = true;
			} else {
				if (found) {
					found = false;
					results.add("\tat [ignored] ...");
				}
				results.add(line);
			}
		}
		
		return StringUtils.join(results, "\n");
	}