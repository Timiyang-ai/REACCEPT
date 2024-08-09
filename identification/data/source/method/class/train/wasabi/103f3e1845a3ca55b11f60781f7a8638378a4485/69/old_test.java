    @Test
    public void getPageExperiments() throws Exception {
        assertNotNull(experimentsResource.getPageExperiments(TESTAPP, TESTPAGE));
    }