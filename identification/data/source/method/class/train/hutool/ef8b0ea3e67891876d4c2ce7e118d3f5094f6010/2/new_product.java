public static String replaceAll(String content, Pattern pattern, String replacementTemplate) {
		if(StrUtil.isEmpty(content)){
			return content;
		}
		
		final Matcher matcher = pattern.matcher(content);
		boolean result = matcher.find();
		if (result) {
			final Set<String> varNums = findAll(PatternPool.GROUP_VAR, replacementTemplate, 1, new HashSet<String>());
			final StringBuffer sb = new StringBuffer();
			do {
				String replacement = replacementTemplate;
				for (String var : varNums) {
					int group = Integer.parseInt(var);
					replacement = replacement.replace("$" + var, matcher.group(group));
				}
				matcher.appendReplacement(sb, escape(replacement));
				result = matcher.find();
			} while (result);
			matcher.appendTail(sb);
			return sb.toString();
		}
		return content;
	}