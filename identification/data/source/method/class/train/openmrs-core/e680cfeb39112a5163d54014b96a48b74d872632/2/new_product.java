public static List<Field> getAllFields(Class<?> fieldClass) {
		List<Field> fields = ClassDataCacher.getInstance().getClassData(fieldClass).getFields();
		return new ArrayList<>(fields);
	}