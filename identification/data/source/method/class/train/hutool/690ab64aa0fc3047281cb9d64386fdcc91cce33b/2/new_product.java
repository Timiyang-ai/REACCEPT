public static Method getDeclaredMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
		Method method = null;
		for (; null != clazz; clazz = clazz.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				break;
			} catch (NoSuchMethodException e) {
				// 继续向上寻找
			}
		}
		return method;
	}