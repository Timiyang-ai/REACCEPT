public List<Field> getInheritedFields(Class<?> subClass) {
		
		List<Field> allFields = getAllFields(subClass);
		for (Iterator<Field> iterator = allFields.iterator(); iterator.hasNext();) {
			Field field = iterator.next();
			if (!hasField(field)) {
				iterator.remove();
			}
		}
		
		return allFields;
	}