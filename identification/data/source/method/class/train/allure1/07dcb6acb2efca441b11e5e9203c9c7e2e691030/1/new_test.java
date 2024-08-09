    @Test
    public void removeTest() throws Exception {
        TestSuiteResult testSuite = testSuiteStorage.get("a");
        assertTrue(testSuite == testSuiteStorage.get("a"));
        testSuiteStorage.remove("a");
        assertFalse(testSuite == testSuiteStorage.get("a"));
    }