@Override
  public void aggregate(int[] docIdSet, int startIndex, int length) {
    Preconditions
        .checkState(_inited, "Method 'aggregate' cannot be called before 'init' for class " + getClass().getName());

    fetchColumnValues(docIdSet, startIndex, length);
    for (int i = 0; i < _aggrFuncContextList.size(); i++) {
      AggregationFunctionContext aggregationFunctionContext = _aggrFuncContextList.get(i);

      for (String column : aggregationFunctionContext.getAggregationColumns()) {
        double[] valuesToAggregate = _columnToValueArrayMap.get(column);

        AggregationFunction function = aggregationFunctionContext.getAggregationFunction();
        function.aggregate(length, _resultHolderArray[i], valuesToAggregate);
      }
    }
  }