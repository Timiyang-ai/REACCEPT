public static Method getMethod(Class<?> clazz, boolean ignoreCase, String methodName, Class<?>... paramTypes) throws SecurityException {
		if (null == clazz || StrUtil.isBlank(methodName)) {
			return null;
		}

		final Method[] methods = getMethods(clazz);
		if (ArrayUtil.isNotEmpty(methods)) {
			for (Method method : methods) {
				if (StrUtil.equals(methodName, method.getName(), ignoreCase)) {
					if (ArrayUtil.isEmpty(paramTypes) || ClassUtil.isAllAssignableFrom(method.getParameterTypes(), paramTypes)) {
						return method;
					}
				}
			}
		}
		return null;
	}