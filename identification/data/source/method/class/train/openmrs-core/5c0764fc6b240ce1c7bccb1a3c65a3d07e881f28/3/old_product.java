public static UserContext getUserContext() { 	
		log.info("Getting user context " + userContextHolder.get() + " from userContextHolder " + userContextHolder);
		if (userContextHolder.get() == null) {
			log.debug("userContext is null. Creating new userContext");
            setUserContext(new UserContext());
        }
		return userContextHolder.get();
	}