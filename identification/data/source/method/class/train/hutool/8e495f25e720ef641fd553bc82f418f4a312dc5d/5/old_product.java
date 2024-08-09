public static String[] cut(String str, int partLength) {
		int len = str.length();
		if(len < partLength){
			return new String[]{str};
		}
		int part = NumberUtil.count(len, partLength);
		final String[] array = new String[part];
		
		for(int i = 0; i < part; i++){
			array[i] = str.substring(i * partLength, (i == part - 1) ? len : (partLength + i * partLength));
		}
		return array;
	}