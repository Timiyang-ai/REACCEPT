@Test
    public void testPin3() throws Exception {
        Clipboard clipboard = new Clipboard();

        Random rng = new Random(12345L);

        Long validId = 123L;
        InitializationAggregation aggregation = new InitializationAggregation(1, 0);
        clipboard.pin(aggregation);

        assertTrue(clipboard.isTracking(0L, aggregation.getTaskId()));
        assertTrue(clipboard.isReady(0L, aggregation.getTaskId()));
    }