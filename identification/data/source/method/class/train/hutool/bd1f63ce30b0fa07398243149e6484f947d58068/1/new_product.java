public static String upperFirst(String str) {
		if(StrUtil.isBlank(str)){
			return str;
		}
		return Character.toUpperCase(str.charAt(0)) + subSuf(str, 1);
	}