@PostConstruct
    public void init() throws IOException {
        createUploadDir();
        logger.log(Level.INFO, "created rest uploads directory - " + restUploadDir.getAbsolutePath());
        createScheduledExecutor();
    }