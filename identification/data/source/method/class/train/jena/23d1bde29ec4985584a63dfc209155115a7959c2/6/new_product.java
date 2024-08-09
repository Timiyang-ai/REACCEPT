@Override
	public boolean canDelete() throws AuthenticationRequiredException {
		final CacheKey key = new CacheKey(Action.Delete, modelNode);
		Boolean retval = cacheGet(key);
		if (retval == null) {
			retval = securityEvaluator.evaluate(
					securityEvaluator.getPrincipal(), Action.Delete, modelNode);
			cachePut(key, retval);
		}
		return retval;
	}