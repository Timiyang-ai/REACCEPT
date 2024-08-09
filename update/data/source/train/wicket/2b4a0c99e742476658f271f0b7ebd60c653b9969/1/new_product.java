public boolean visitObject(final Object object)
	{
		return clazz.isAssignableFrom(object.getClass());
	}