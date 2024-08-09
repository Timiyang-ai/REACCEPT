public static boolean isBean(Class<?> clazz) {
		return hasSetter(clazz);
	}