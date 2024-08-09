public static Method getMethod(Class<?> beanClass, String name) throws SecurityException {
		final Method[] methods = getMethods(beanClass);
		for (Method method : methods) {
			if ((name.equals(method.getName()))) {
				return method;
			}
		}
		return null;
	}