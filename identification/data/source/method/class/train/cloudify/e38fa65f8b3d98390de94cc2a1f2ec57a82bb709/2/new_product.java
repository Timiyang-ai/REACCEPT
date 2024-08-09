@PostConstruct
	public void init()
			throws IOException, RestErrorException {
		try {
		createUploadDir();
		createScheduledExecutor();
		} catch (IOException e) {
			logger.log(Level.WARNING, "failed to initialize UploadRepo, got IOException: - " + e.getMessage());
			throw e;
		} catch (RestErrorException e) {
			logger.log(Level.WARNING, "failed to initialize UploadRepo, got RestErrorException: - " + e.getMessage());
			throw e;
		}
	}