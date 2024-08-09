public static String replace(CharSequence str, int fromIndex, CharSequence searchStr, CharSequence replacement, boolean ignoreCase) {
		if (isEmpty(str) || isEmpty(searchStr)) {
			return str(str);
		}
		if (null == replacement) {
			replacement = EMPTY;
		}

		final int strLength = str.length();
		final int searchStrLength = searchStr.length();
		if (fromIndex > strLength) {
			return str(str);
		} else if (fromIndex < 0) {
			fromIndex = 0;
		}

		final StrBuilder result = StrBuilder.create(strLength + 16);
		if (0 != fromIndex) {
			result.append(str.subSequence(0, fromIndex));
		}

		int preIndex = fromIndex;
		int index;
		while ((index = indexOf(str, searchStr, preIndex, ignoreCase)) > -1) {
			result.append(str.subSequence(preIndex, index));
			result.append(replacement);
			preIndex = index + searchStrLength;
		}

		if (preIndex < strLength) {
			// 结尾部分
			result.append(str.subSequence(preIndex, strLength));
		}
		return result.toString();
	}