public static UserContext getUserContext() {
		Object[] arr = userContextHolder.get();
		
		if (log.isTraceEnabled())
			log.trace("Getting user context " + arr + " from userContextHolder " + userContextHolder);
		
		if (arr == null) {
			log.trace("userContext is null. Creating new userContext");
			setUserContext(new UserContext());
		}
		return (UserContext) userContextHolder.get()[0];
	}