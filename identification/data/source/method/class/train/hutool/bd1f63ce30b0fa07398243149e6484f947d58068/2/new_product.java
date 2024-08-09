public static String upperFirst(CharSequence str) {
		if(null == str){
			return null;
		}
		if(str.length() > 0){
			char firstChar = str.charAt(0);
			if(Character.isLowerCase(firstChar)){
				return Character.toUpperCase(firstChar) + subSuf(str, 1);
			}
		}
		return str.toString();
	}