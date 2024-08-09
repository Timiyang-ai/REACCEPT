public static int utf8EncodedLength(@Nullable CharSequence sequence) {
		if (StringUtils.isEmpty(sequence)) {
			return 0;
		}
		return Utf8.encodedLength(sequence);
	}