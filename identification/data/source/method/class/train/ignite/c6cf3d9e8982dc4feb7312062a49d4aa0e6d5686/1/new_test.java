@Test
    public void testAggregate() {
        BinaryClassificationPointwiseMetricStatsAggregator<Double> aggregator = new BinaryClassificationPointwiseMetricStatsAggregator<>();
        assertEquals(null, aggregator.getFalseLabel());
        assertEquals(null, aggregator.getTruthLabel());

        aggregator.initByContext(new BinaryClassificationEvaluationContext<>(0., 1.));
        assertEquals(0., aggregator.getFalseLabel(), 0.);
        assertEquals(1., aggregator.getTruthLabel(), 0.);
        assertEquals(0, aggregator.getTrueNegative());
        assertEquals(0, aggregator.getFalseNegative());
        assertEquals(0, aggregator.getTruePositive());
        assertEquals(0, aggregator.getFalsePositive());

        aggregator.aggregate(mdl, VectorUtils.of(0.).labeled(0.));
        aggregator.aggregate(mdl, VectorUtils.of(1.).labeled(0.));
        aggregator.aggregate(mdl, VectorUtils.of(1.).labeled(1.));
        aggregator.aggregate(mdl, VectorUtils.of(0.).labeled(1.));

        assertEquals(1, aggregator.getTrueNegative());
        assertEquals(1, aggregator.getFalseNegative());
        assertEquals(1, aggregator.getTruePositive());
        assertEquals(1, aggregator.getFalsePositive());
    }