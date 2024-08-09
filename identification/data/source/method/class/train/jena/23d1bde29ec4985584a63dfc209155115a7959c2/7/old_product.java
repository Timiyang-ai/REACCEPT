@Override
	public boolean canCreate()
	{
		final CacheKey key = new CacheKey(Action.Create, modelNode);
		Boolean retval = cacheGet(key);
		if (retval == null)
		{
			retval = securityEvaluator.evaluate(Action.Create, modelNode);
			cachePut(key, retval);
		}
		return retval;
	}