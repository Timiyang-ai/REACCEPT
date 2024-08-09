public static String extractMulti(Pattern pattern, String content, String template) {
		if(null == content || null == pattern || null == template){
			return null;
		}
		
		HashSet<String> varNums = findAll(GROUP_VAR, template, 1, new HashSet<String>());
		
		Matcher matcher = pattern.matcher(content);
		if (matcher.find()) {
			for (String var : varNums) {
				int group = Integer.parseInt(var);
				template = template.replace("$" + var, matcher.group(group));
			}
			return template;
		}
		return null;
	}