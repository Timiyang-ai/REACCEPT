@SuppressWarnings("unchecked")
	public boolean isCollectionField(Field field) {
		if (isCollection(field.getType())) {
			try {
				ParameterizedType type = (ParameterizedType) field.getGenericType();
				if (type.getActualTypeArguments()[0] instanceof Class) {
					return (parametrizedClass.isAssignableFrom((Class) type.getActualTypeArguments()[0]));
				} else if (type.getActualTypeArguments()[0] instanceof TypeVariable) {
					return isSuperClass((TypeVariable<?>) type.getActualTypeArguments()[0]);
				} else {}
			}
			catch (ClassCastException e) {
				// Do nothing.  If this exception is thrown, then field is not a Collection of OpenmrsObjects
			}
		}
		return false;
	}