    @Test
    public void getView() {
        assertNotNull(metadata.getViewRepository().getView(testMasterEntity, View.LOCAL));
        assertNotNull(metadata.getViewRepository().getView(testMasterEntity, View.MINIMAL));
        assertNotNull(metadata.getViewRepository().getView(testMasterEntity, "withDetails"));
        assertNotNull(metadata.getViewRepository().getView(testMasterEntity, "withDetail"));
    }