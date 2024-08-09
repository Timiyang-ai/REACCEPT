public static <T> T invokeMethod(final Object obj, final String methodName, final Object[] args,
			final Class<?>[] parameterTypes) {
		Method method = getAccessibleMethod(obj.getClass(), methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + ']');
		}

		try {
			return (T) method.invoke(obj, args);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}