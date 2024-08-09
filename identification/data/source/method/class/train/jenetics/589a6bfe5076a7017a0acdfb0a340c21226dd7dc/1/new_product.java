public static int count(final byte value) {
		return BIT_SET_TABLE[value + BIT_SET_TABLE_INDEX_OFFSET];
	}