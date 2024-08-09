@TestTargetNew(
        level = TestLevel.PARTIAL_COMPLETE,
        notes = "",
        method = "getLogger",
        args = {java.lang.String.class}
    )
    public void test_init_logger() throws Exception {
        Properties p = new Properties();
        p.put("testGetLogger_Normal_ANewLogger2.level", "ALL");
        LogManager.getLogManager().readConfiguration(
                EnvironmentHelper.PropertiesToInputStream(p));

        assertNull(LogManager.getLogManager().getLogger(
                "testGetLogger_Normal_ANewLogger2"));
        SecurityManager originalSecurityManager = System.getSecurityManager();
        try {
            Logger logger = Logger
                            .getLogger("testGetLogger_Normal_ANewLogger2");
            System.setSecurityManager(new MockSecurityManager());
            try {
                logger.setLevel(Level.ALL);
                fail("should throw SecurityException");
            } catch (SecurityException e) {
                // expected
            }
            try {
                logger.setParent(Logger.getLogger(""));
                fail("should throw SecurityException");
            } catch (SecurityException e) {
                // expected
            }
        } finally {
            System.setSecurityManager(originalSecurityManager);
        }
    }