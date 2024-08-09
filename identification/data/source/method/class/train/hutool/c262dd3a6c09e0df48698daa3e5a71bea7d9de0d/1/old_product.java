public static Method getPublicMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) throws NoSuchMethodException, SecurityException {
		try {
			return clazz.getMethod(methodName, paramTypes);
		} catch (NoSuchMethodException ex) {
			return getDeclaredMethod(clazz, methodName, paramTypes);
		}
	}