public static Field getDeclaredField(Class<?> clazz, String fieldName) throws  SecurityException{
		if(null == clazz || StrUtil.isBlank(fieldName)){
			return null;
		}
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			//e.printStackTrace();
		}
		return null;
	}