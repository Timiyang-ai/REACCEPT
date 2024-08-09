    public void test_loadLog() throws Exception {
        CTLogInfo log = CTLogStoreImpl.loadLog(
                new ByteArrayInputStream(LOGS_SERIALIZED[0].getBytes(StandardCharsets.US_ASCII)));
        assertEquals(LOGS[0], log);

        File testFile = writeFile(LOGS_SERIALIZED[0]);
        log = CTLogStoreImpl.loadLog(testFile);
        assertEquals(LOGS[0], log);

        // Empty log file, used to mask fallback logs
        assertEquals(null, CTLogStoreImpl.loadLog(new ByteArrayInputStream(new byte[0])));
        try {
            CTLogStoreImpl.loadLog(new ByteArrayInputStream(
                    "randomgarbage".getBytes(StandardCharsets.US_ASCII)));
            fail("InvalidLogFileException not thrown");
        } catch (CTLogStoreImpl.InvalidLogFileException e) {}

        try {
            CTLogStoreImpl.loadLog(new File("/nonexistent"));
            fail("FileNotFoundException not thrown");
        } catch (FileNotFoundException e) {}
    }