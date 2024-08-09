public static Map<String, Object> beanToMap(Object bean, boolean isToUnderlineCase, boolean ignoreNullValue) {
		return beanToMap(bean, new LinkedHashMap<String, Object>(), isToUnderlineCase, ignoreNullValue);
	}