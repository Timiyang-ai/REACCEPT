public static boolean isBean(Class<?> clazz){
		if(ClassUtil.isNormalClass(clazz)){
			final Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if(method.getParameterTypes().length == 1 && method.getName().startsWith("set")){
					//检测包含标准的setXXX方法即视为标准的JavaBean
					return true;
				}
			}
		}
		return false;
	}