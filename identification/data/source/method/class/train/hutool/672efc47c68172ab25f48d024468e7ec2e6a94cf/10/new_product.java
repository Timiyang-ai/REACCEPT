public static String format(CharSequence template, Map<?, ?> map) {
		if(null == template){
			return null;
		}
		if (null == map || map.isEmpty()) {
			return template.toString();
		}

		final String template2 = template.toString();
		for (Entry<?, ?> entry : map.entrySet()) {
			template = template2.replace("{" + entry.getKey() + "}", utf8Str(entry.getValue()));
		}
		return template2;
	}