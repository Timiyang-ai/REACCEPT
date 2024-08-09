@SuppressWarnings("unchecked")
	public boolean isCollectionField(Field field) {
		if (isCollection(field.getType())) {
			try {
				ParameterizedType type = (ParameterizedType) field.getGenericType();
				return (parametrizedClass.isAssignableFrom((Class) type.getActualTypeArguments()[0]));
			}
			catch (ClassCastException e) {
				// Do nothing.  If this exception is thrown, then field is not a Collection of OpenmrsObjects
			}
		}
		return false;
	}