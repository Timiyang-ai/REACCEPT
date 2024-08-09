public static UserContext getUserContext() {
		Object[] arr = userContextHolder.get();
		
		if (log.isTraceEnabled())
			log.trace("Getting user context " + arr + " from userContextHolder " + userContextHolder);
		
		if (arr == null) {
			log.trace("userContext is null.");
			throw new APIException(
			        "A user context must first be passed to setUserContext()...use Context.openSession() (and closeSession() to prevent memory leaks!) before using the API");
		}
		return (UserContext) userContextHolder.get()[0];
	}