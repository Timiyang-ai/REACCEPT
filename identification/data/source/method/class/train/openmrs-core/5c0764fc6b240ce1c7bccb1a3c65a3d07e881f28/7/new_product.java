public static UserContext getUserContext() {
		Object[] arr = userContextHolder.get();
		log.trace("Getting user context {} from userContextHolder {}", Arrays.toString(arr), userContextHolder);

		if (arr == null) {
			log.trace("userContext is null.");
			throw new APIException(
			        "A user context must first be passed to setUserContext()...use Context.openSession() (and closeSession() to prevent memory leaks!) before using the API");
		}
		return (UserContext) userContextHolder.get()[0];
	}