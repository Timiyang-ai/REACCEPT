    @Test
    public void getLastLogTest() throws Exception
    {
        startTest(false);
        DebugLogger log = new DebugLogger(true, 10);
        log.onLogEntry(2, "tag", "2");
        log.onLogEntry(2, "tag", "3");
        log.onLogEntry(2, "tag", "4");
        log.onLogEntry(2, "tag", "5");
        log.onLogEntry(2, "tag", "6");
        log.onLogEntry(2, "tag", "7");
        log.onLogEntry(2, "tag", "8");
        log.onLogEntry(2, "tag", "9");
        log.onLogEntry(2, "tag", "10");
        log.onLogEntry(2, "tag", "11");
        log.onLogEntry(2, "tag", "12");
        String last = log.getLastLog();
        assertNotNull(last);
        assertTrue(last.endsWith("12"));
        succeed();
    }