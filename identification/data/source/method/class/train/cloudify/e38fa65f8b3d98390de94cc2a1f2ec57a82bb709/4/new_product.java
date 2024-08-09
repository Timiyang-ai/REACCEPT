public void init() throws RestErrorException {
		log(Level.INFO, "Initializing upload repo.");
		createUploadDir();
		createScheduledExecutor();
	}