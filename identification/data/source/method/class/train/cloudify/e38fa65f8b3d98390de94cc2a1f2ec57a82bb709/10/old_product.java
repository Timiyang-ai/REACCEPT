@PostConstruct
	public void init(final File restTempFolder)
			throws IOException, RestErrorException {
		try {
			logger.warning("***** starting uploadReop.init, setting baseDir to: " + restTempFolder);
			this.baseDir = restTempFolder;
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