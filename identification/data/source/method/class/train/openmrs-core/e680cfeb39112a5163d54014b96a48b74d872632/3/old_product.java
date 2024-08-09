@SuppressWarnings("unchecked")
	public List<Field> getInheritedFields(Class subClass) {
		
		List<Field> allFields = getAllFields(subClass);
		for (Iterator iterator = allFields.iterator(); iterator.hasNext();) {
			Field field = (Field) iterator.next();
			if (!hasField(field)) {
				iterator.remove();
			}
		}
		
		return allFields;
	}