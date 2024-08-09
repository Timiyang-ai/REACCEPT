public static String format(CharSequence template, Object... params) {
		if(null == template){
			return null;
		}
		if (ArrayUtil.isEmpty(params) || isBlank(template)) {
			return template.toString();
		}
		return StrFormatter.format(template.toString(), params);
	}