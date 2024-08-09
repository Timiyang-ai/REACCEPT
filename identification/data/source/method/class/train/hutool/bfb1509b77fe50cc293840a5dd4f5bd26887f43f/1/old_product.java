public static void log(String template, Object... values){
		System.out.println(StrUtil.format(template, values));
	}