public static UserContext getUserContext() {
		Object[] arr = userContextHolder.get();
		
		if (log.isInfoEnabled())
			log.info("Getting user context " + arr + " from userContextHolder " + userContextHolder);
		
		if (arr == null) {
			log.debug("userContext is null. Creating new userContext");
            setUserContext(new UserContext());
        }
		return (UserContext)userContextHolder.get()[0];
	}