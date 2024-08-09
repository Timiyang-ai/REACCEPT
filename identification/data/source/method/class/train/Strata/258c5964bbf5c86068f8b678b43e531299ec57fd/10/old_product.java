public CalculationResult execute(ScenarioMarketData marketData) {
    Result<?> result;
    try {
      DefaultCalculationMarketData calculationData = new DefaultCalculationMarketData(marketData, marketDataMappings);
      result = Result.success(function.execute(target, calculationData, reportingRules));
    } catch (Exception e) {
      result = Result.failure(e);
    }
    return CalculationResult.of(target, rowIndex, columnIndex, result);
  }