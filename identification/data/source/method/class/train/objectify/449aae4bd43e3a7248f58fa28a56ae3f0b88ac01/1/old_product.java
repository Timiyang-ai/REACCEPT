@Override
	public Query<T> keysOnly()
	{
		QueryImpl<T> q = createQuery();
		q.setKeysOnly();
		return q;
	}