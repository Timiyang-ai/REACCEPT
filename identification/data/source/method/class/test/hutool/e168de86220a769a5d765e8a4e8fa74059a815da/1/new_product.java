@SuppressWarnings("unchecked")
	public static <T> T getProperty(Object bean, String expression) {
		return (T) BeanPath.create(expression).get(bean);
	}