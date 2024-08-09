@Override
	public boolean canCreate() throws AuthenticationRequiredException {
		final CacheKey key = new CacheKey(Action.Create, modelNode);
		Boolean retval = cacheGet(key);
		if (retval == null) {
			retval = securityEvaluator.evaluate(
					securityEvaluator.getPrincipal(), Action.Create, modelNode);
			cachePut(key, retval);
		}
		return retval;
	}