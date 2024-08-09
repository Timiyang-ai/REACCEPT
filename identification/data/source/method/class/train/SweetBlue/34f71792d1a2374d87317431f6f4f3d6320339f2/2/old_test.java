    @Test
    public void getLastLogsTest() throws Exception
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
        List<String> list = log.getLastLogs(5);
        assertTrue(list.size() == 5);
        assertTrue(list.get(4).endsWith("12"));
        succeed();
    }