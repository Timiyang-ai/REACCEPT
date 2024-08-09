public static Field getField(Class<?> beanClass, String name) throws SecurityException {
		final Field[] fields = getFields(beanClass);
		for (Field field : fields) {
			if ((name.equals(field.getName()))) {
				return field;
			}
		}
		return null;
	}