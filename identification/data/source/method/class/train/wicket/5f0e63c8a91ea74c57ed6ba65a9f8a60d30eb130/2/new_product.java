@Override
	public PageParameters set(final String name, final Object value, Type type)
	{
		int position = getPosition(name);
		set(name, value, position, type);
		return this;
	}