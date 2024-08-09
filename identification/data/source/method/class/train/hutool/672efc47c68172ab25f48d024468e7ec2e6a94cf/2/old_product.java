public static String format(String template, Object... params) {
		if (CollectionUtil.isEmpty(params) || isBlank(template)) {
			return template;
		}
		return StrFormatter.format(template, params);
	}