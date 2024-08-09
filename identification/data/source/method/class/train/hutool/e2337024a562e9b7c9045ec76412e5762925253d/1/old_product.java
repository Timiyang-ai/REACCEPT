public static Field getDeclaredField(Class<?> clazz, String fieldName) throws NoSuchFieldException, SecurityException{
		if(null == clazz || StrUtil.isBlank(fieldName)){
			return null;
		}
		return clazz.getDeclaredField(fieldName);
	}