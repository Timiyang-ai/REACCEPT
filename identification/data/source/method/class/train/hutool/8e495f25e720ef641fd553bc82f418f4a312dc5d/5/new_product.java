public static String[] cut(CharSequence str, int partLength) {
		if(null == str){
			return null;
		}
		int len = str.length();
		if(len < partLength){
			return new String[]{str.toString()};
		}
		int part = NumberUtil.count(len, partLength);
		final String[] array = new String[part];
		
		final String str2 = str.toString();
		for(int i = 0; i < part; i++){
			array[i] = str2.substring(i * partLength, (i == part - 1) ? len : (partLength + i * partLength));
		}
		return array;
	}