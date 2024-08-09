public static boolean checkNameing(String str)
	{
		char[] commonArray = new char[str.length()];
		int len = 0;
		if (str == null || (len = str.length()) == 0)
		{
			return false;
		}
		str.getChars(0, len, commonArray, 0);
		int index = 0;
		char word = commonArray[index++];
		//首字母判断  不为数字 , .
		if (word >= 46 && word <= 57)
			setLog(1, word);
		//尾字母判断
		else if (commonArray[len - 1] == 46)
			setLog(len, 46);
		else
			while (true)
			{
				if (word < 36 || word > 122 || chars[word - 36] == 0)
				{
					setLog(index + 1, word);
					return false;
				}
				if (index == len)
					return true;
				word = commonArray[index++];
			}
		return false;
	}