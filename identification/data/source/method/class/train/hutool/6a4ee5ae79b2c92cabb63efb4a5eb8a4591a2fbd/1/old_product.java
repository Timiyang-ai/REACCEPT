public static <T> boolean contains(T[] array, T value) {
		final Class<?> componetType = array.getClass().getComponentType();
		boolean isPrimitive = false;
		if (null != componetType) {
			isPrimitive = componetType.isPrimitive();
		}
		for (T t : array) {
			if (t == value) {
				return true;
			} else if (false == isPrimitive && null != value && value.equals(t)) {
				return true;
			}
		}
		return false;
	}