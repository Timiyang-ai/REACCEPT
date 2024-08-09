public static boolean isEmpty(final Object array) {
		if(null == array) {
			return true;
		}else if(isArray(array)) {
			return 0 == Array.getLength(array);
		}
		throw new UtilException("Object to provide is not a Array !");
	}