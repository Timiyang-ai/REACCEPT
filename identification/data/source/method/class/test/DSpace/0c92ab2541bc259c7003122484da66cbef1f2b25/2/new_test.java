    @After
    public void shutdown() {
        if (dsm != null) {
            dsm.shutdown();
        }
        dsm = null;
        configurationService = null;
    }