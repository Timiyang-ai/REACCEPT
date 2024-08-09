    @Test
    public void init() {
        bootstrap.init();
        assertNotNull(NulsConfig.MODULES_CONFIG);
        assertNotNull(NulsConfig.NULS_CONFIG);
        assertTrue(SpringLiteContext.isInitSuccess());
        assertNotNull(NulsConfig.VERSION);
        assertNotNull(ValidatorManager.isInitSuccess());
    }