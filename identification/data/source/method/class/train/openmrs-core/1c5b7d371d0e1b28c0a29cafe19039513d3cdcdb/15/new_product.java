public static void logout() {
		if (!isSessionOpen())
			return; // fail early if there isn't even a session open
			
		if (log.isDebugEnabled())
			log.debug("Logging out : " + getAuthenticatedUser());
		
		getUserContext().logout();
		
		//reset the UserContext object (usually cleared out by closeSession() soon after this)
		setUserContext(new UserContext()); 
	}