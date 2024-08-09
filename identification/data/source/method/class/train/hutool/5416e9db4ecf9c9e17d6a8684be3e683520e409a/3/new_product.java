public static String toJsonStr(Object obj){
		if(obj instanceof String) {
			return (String)obj;
		}
		return toJsonStr(parse(obj));
	}