public static List<String> split(final String str, final char separatorChar, int expectParts) {
		if (str == null) {
			return null;
		}
		final int len = str.length();
		if (len == 0) {
			return Lists.emptyList();
		}
		final List<String> list = new ArrayList<String>(expectParts);
		int i = 0, start = 0;
		boolean lastMatch = false;
		while (i < len) {
			if (str.charAt(i) == separatorChar) {

				list.add(str.substring(start, i));
				lastMatch = true;
				start = ++i;
				continue;
			}
			lastMatch = false;
			i++;
		}
		if (lastMatch) {
			list.add(str.substring(start, i));
		}
		return list;
	}