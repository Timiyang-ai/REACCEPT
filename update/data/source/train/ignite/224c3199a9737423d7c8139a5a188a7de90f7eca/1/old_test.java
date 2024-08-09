@Ignore("https://issues.apache.org/jira/browse/IGNITE-9470")
    @Test
    public void testUpdateSingleValue_LocalQuery_SingleNode() throws Exception {
        updateSingleValue(true, true);
    }