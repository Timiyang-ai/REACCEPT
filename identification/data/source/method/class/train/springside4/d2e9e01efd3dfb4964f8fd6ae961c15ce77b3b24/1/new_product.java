public static List<String> split(@Nullable final String str, final char separatorChar, int expectParts) {

		if (str == null) {
			return null;
		}
		final int len = str.length();
		if (len == 0) {
			return ListUtil.emptyList();
		}
		final List<String> list = new ArrayList<String>(expectParts);
		int i = 0;
		int start = 0;
		boolean match = false;
		while (i < len) {
			if (str.charAt(i) == separatorChar) {
				if (match) {
					list.add(str.substring(start, i));
					match = false;
				}
				start = ++i;
				continue;
			}
			match = true;
			i++;
		}
		if (match) {
			list.add(str.substring(start, i));
		}
		return list;
	}