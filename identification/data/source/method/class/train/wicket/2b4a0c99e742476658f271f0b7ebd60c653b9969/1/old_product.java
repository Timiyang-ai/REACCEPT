public boolean visitObject(final Object object)
	{
		return clazz == null || clazz.isAssignableFrom(object.getClass());
	}