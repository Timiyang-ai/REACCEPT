public static String lowerFirst(CharSequence str) {
		if(null == str){
			return null;
		}
		if(str.length() > 0){
			char firstChar = str.charAt(0);
			if(Character.isUpperCase(firstChar)){
				return Character.toLowerCase(firstChar) + subSuf(str, 1);
			}
		}
		return str.toString();
	}