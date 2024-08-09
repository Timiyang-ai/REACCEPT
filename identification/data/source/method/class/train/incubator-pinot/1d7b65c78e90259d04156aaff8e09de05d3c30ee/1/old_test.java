@Test
  void testAggregate() {
    double[] valuesToAggregate = new double[NUM_VALUES_TO_AGGREGATE];
    double expected = DEFAULT_VALUE;

    for (int i = 0; i < NUM_VALUES_TO_AGGREGATE; i++) {
      valuesToAggregate[i] = _random.nextDouble();
      expected = Math.max(expected, valuesToAggregate[i]);
    }

    MaxAggregationFunction maxAggregationFunction = new MaxAggregationFunction();
    ResultHolder resultHolder = ResultHolderFactory.getResultHolder(maxAggregationFunction, 1 /* maxNumResults */);

    maxAggregationFunction.aggregate(NUM_VALUES_TO_AGGREGATE, resultHolder, valuesToAggregate);
    double actual = resultHolder.getDoubleResult();

    Assert.assertEquals(actual, expected, "Max Aggregation test failed Expected: " + expected + " Actual: " + actual);
  }