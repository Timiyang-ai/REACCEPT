public static void error(String template, Object... values){
		System.err.println(StrUtil.format(template, values));
	}