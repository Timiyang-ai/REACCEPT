@Test
    public void testToString() {
        PredictionsAggregator aggr = (PredictionsAggregator)doubles -> null;
        assertTrue(!aggr.toString().isEmpty());
        assertTrue(!aggr.toString(true).isEmpty());
        assertTrue(!aggr.toString(false).isEmpty());

        WeightedPredictionsAggregator aggregator = new WeightedPredictionsAggregator(EMPTY_DOUBLE_ARRAY);
        assertTrue(!aggregator.toString().isEmpty());
        assertTrue(!aggregator.toString(true).isEmpty());
        assertTrue(!aggregator.toString(false).isEmpty());
    }