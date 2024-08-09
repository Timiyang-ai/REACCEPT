public static <T> boolean contains(T[] array, T value) {
		return indexOf(array, value) > INDEX_NOT_FOUND;
	}