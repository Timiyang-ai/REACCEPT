public static String format(String template, Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			return template;
		}
		
		for (Entry<String, Object> entry : map.entrySet()) {
			template.replace("{" + entry.getKey() + "}", entry.getValue().toString());
		}
		return template;
	}