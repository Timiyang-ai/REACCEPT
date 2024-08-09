@Ignore("https://issues.apache.org/jira/browse/IGNITE-9470")
    @Test
    public void testJoinTransactional_SingleNode() throws Exception {
        joinTransactional(true, false);
    }