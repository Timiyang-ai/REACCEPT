public static Method getDeclaredMethodOfObj(Object obj, String methodName, Object... args) throws NoSuchMethodException, SecurityException {
		return getDeclaredMethod(obj.getClass(), methodName, getClasses(args));
	}