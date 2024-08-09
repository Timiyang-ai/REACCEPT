@Test
    public void testPrefop() {
        try {
        	String jobid = "z0.5c81361a38b9f349c8bb5288";
            Configuration cfg = new Configuration(Region.autoRegion());
            OperationManager operationManager = new OperationManager(TestConfig.testAuth, cfg);
            OperationStatus status = operationManager.prefop(jobid);
            Assert.assertEquals(0, status.code);
        } catch (QiniuException ex) {
            Assert.assertEquals(612, ex.code());
        }
    }