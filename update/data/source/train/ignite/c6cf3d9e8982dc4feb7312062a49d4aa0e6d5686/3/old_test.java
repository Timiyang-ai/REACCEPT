@Test
    public void testToString() {
        PredictionsAggregator aggr = (PredictionsAggregator)doubles -> null;
        assertTrue(aggr.toString().length() > 0);
        assertTrue(aggr.toString(true).length() > 0);
        assertTrue(aggr.toString(false).length() > 0);

        WeightedPredictionsAggregator aggregator = new WeightedPredictionsAggregator(new double[] {});
        assertTrue(aggregator.toString().length() > 0);
        assertTrue(aggregator.toString(true).length() > 0);
        assertTrue(aggregator.toString(false).length() > 0);
    }