public static boolean isEmpty(final Object array) {
		return array == null || (false == isArray(array)) || Array.getLength(array) == 0;
	}