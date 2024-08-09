public static Method[] getMethods(Class<?> clazz, Filter<Method> filter) throws SecurityException {
		if (null == clazz) {
			return null;
		}

		final Method[] methods = getMethods(clazz);
		if (null == filter) {
			return methods;
		}

		final List<Method> methodList = new ArrayList<>();
		for (Method method : methods) {
			if (filter.accept(method)) {
				methodList.add(method);
			}
		}
		return methodList.toArray(new Method[methodList.size()]);
	}