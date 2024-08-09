public static String removeTags(String str)
	{
		StringBuilder builder = new StringBuilder(str.length());
		boolean inTag = false;

		for (int i = 0; i < str.length(); i++)
		{
			char currentChar = str.charAt(i);

			if (currentChar == '<')
			{
				inTag = true;
			}
			else if (currentChar == '>')
			{
				inTag = false;
			}
			else if (!inTag)
			{
				builder.append(currentChar);
			}
		}

		return builder.toString();
	}