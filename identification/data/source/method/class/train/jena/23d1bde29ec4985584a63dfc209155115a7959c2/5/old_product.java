@Override
	public boolean canUpdate()
	{
		final CacheKey key = new CacheKey(Action.Update, modelNode);
		Boolean retval = cacheGet(key);
		if (retval == null)
		{
			retval = securityEvaluator.evaluate(Action.Update, modelNode);
			cachePut(key, retval);
		}
		return retval;
	}