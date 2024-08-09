@Test
  void testAggregateGroupBySV() {
    double[] valuesToAggregate = new double[NUM_VALUES_TO_AGGREGATE];
    int[] groupKeysForValues = new int[NUM_VALUES_TO_AGGREGATE];

    Int2DoubleOpenHashMap expectedGroupByResultMap = new Int2DoubleOpenHashMap();
    expectedGroupByResultMap.defaultReturnValue(DEFAULT_VALUE);

    for (int i = 0; i < MAX_NUM_GROUP_KEYS; i++) {
      groupKeysForValues[i] = Math.abs(_random.nextInt()) % MAX_NUM_GROUP_KEYS;
    }

    for (int i = 0; i < NUM_VALUES_TO_AGGREGATE; i++) {
      valuesToAggregate[i] = _random.nextDouble();

      int key = groupKeysForValues[i];
      double oldValue = expectedGroupByResultMap.get(key);
      double newValue = Math.min(oldValue, valuesToAggregate[i]);
      expectedGroupByResultMap.put(key, newValue);
    }

    MinAggregationFunction minAggregationFunction = new MinAggregationFunction();
    GroupByResultHolder resultHolder =
        ResultHolderFactory.getGroupByResultHolder(minAggregationFunction, MAX_NUM_GROUP_KEYS);

    minAggregationFunction.aggregateGroupBySV(NUM_VALUES_TO_AGGREGATE, groupKeysForValues, resultHolder,
        valuesToAggregate);

    for (Map.Entry<Integer, Double> entry : expectedGroupByResultMap.entrySet()) {
      int key = entry.getKey();
      double expected = entry.getValue();
      double actual = resultHolder.getDoubleResult(key);
      Assert.assertEquals(actual, expected, "Min Aggregation test failed Expected: " + expectedGroupByResultMap + " Actual: " + actual);
    }
  }