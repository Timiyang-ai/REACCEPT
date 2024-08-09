    @Test
    public void getLogListTest() throws Exception
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
        List<String> logList = log.getLogList();
        assertTrue(logList.size() == 10);
        assertTrue(logList.get(9).endsWith("12"));
        succeed();
    }