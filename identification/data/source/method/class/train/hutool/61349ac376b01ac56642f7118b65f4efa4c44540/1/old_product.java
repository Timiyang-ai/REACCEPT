public static String sub(String string, int fromIndex, int toIndex) {
		int len = string.length();

		if (fromIndex < 0) {
			fromIndex = len + fromIndex;

			if (toIndex == 0) {
				toIndex = len;
			}
		}

		if (toIndex < 0) {
			toIndex = len + toIndex;
		}

		if (toIndex < fromIndex) {
			int tmp = fromIndex;
			fromIndex = toIndex;
			toIndex = tmp;
		}

		if (fromIndex == toIndex) {
			return EMPTY;
		}

		char[] strArray = string.toCharArray();
		char[] newStrArray = Arrays.copyOfRange(strArray, fromIndex, toIndex);
		return new String(newStrArray);
	}