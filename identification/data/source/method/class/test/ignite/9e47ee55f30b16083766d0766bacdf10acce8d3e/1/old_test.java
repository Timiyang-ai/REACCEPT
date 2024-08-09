@Ignore("https://issues.apache.org/jira/browse/IGNITE-7265")
    @Test
    public void testIterateConcurrentPutRemove_1() throws Exception {
        MAX_PER_PAGE = 1;

        iterateConcurrentPutRemove();
    }