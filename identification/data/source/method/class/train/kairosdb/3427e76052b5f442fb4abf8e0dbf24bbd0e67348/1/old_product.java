public static boolean isNumber(String s)
	{
		checkNotNull(s);

		if (s.isEmpty())
			return false;

		int start = 0;
		char firstChar = s.charAt(0);
		if (firstChar == '+' || firstChar == '-' || firstChar == '.')
		{
			start = 1;
			if (s.length() == 1)
				return false;
		}

		for (int i = start; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if (!Character.isDigit(c) && c != '.')
				return false;
		}

		//noinspection RedundantIfStatement
		if (s.charAt(s.length() - 1) == '.')
			return false; // can't have trailing period

		return true;
	}