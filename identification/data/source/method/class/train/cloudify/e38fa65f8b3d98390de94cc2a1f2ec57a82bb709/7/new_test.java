    @Before
    public void init() 
    		throws RestErrorException {
        repo = new UploadRepo();
        repo.init();
		repo.setBaseDir(new File(CloudifyConstants.REST_FOLDER));
		repo.createUploadDir();
    }