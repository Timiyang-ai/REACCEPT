@SuppressWarnings("unchecked")
	public static List<Field> getAllFields(Class<?> fieldClass) {
		return new ClassData(fieldClass).getFields();
	}