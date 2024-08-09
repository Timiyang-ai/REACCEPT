public void init()
	{
		log.debug("init");
		// register Sakai permissions for this tool
		functionManager.registerFunction(PERM_ADMIN);
		functionManager.registerFunction(PERM_SEND);
	}