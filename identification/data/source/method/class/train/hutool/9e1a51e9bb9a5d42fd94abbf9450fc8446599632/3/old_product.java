public static Method getMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) throws SecurityException {
		if (null == clazz || StrUtil.isBlank(methodName)) {
			return null;
		}

		final Method[] methods = getMethods(clazz);
		for (Method method : methods) {
			if (methodName.equals(method.getName())) {
				if (ArrayUtil.isEmpty(paramTypes) || ClassUtil.isAllAssignableFrom(method.getParameterTypes(), paramTypes)) {
					return method;
				}
			}
		}
		return null;
	}