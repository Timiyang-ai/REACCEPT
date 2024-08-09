public static String[] split(CharSequence str, CharSequence delimiter) {
		if (str == null) {
			return null;
		}
		
		final String str2 = str.toString();
		if (str2.trim().length() == 0) {
			return new String[] { str2 };
		}

		int dellen = delimiter.length(); // del length
		int maxparts = (str.length() / dellen) + 2; // one more for the last
		int[] positions = new int[maxparts];

		int i, j = 0;
		int count = 0;
		positions[0] = -dellen;
		final String delimiter2 = delimiter.toString();
		while ((i = str2.indexOf(delimiter2, j)) != -1) {
			count++;
			positions[count] = i;
			j = i + dellen;
		}
		count++;
		positions[count] = str.length();

		String[] result = new String[count];

		for (i = 0; i < count; i++) {
			result[i] = str2.substring(positions[i] + dellen, positions[i + 1]);
		}
		return result;
	}