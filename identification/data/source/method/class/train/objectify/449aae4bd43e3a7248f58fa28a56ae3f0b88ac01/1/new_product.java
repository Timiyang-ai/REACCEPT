@Override
	public QueryKeys<T> keys()
	{
		QueryImpl<T> q = createQuery();
		q.setKeysOnly();
		return new QueryKeysImpl<T>(q);
	}