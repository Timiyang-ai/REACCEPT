@Override
	public boolean canDelete()
	{
		final CacheKey key = new CacheKey(Action.Delete, modelNode);
		Boolean retval = cacheGet(key);
		if (retval == null)
		{
			retval = securityEvaluator.evaluate(Action.Delete, modelNode);
			cachePut(key, retval);
		}
		return retval;
	}