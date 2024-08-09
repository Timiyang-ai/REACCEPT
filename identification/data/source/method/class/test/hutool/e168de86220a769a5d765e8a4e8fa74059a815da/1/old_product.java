public static Object getProperty(Object bean, String expression) {
		return BeanPath.create(expression).get(bean);
	}